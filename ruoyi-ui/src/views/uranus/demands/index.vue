<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="需求名称" prop="demandsName">
        <el-input
          v-model="queryParams.demandsName"
          placeholder="请输入需求名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="需求紧急程度，划分三个档次" prop="demandsUrgency">
        <el-input
          v-model="queryParams.demandsUrgency"
          placeholder="请输入需求紧急程度，划分三个档次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预计完成时间" prop="planTime">
        <el-date-picker clearable
          v-model="queryParams.planTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预计完成时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="实际完成时间" prop="completeTime">
        <el-date-picker clearable
          v-model="queryParams.completeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择实际完成时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['uranus:demands:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['uranus:demands:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['uranus:demands:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['uranus:demands:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="demandsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="demandsId" />
      <el-table-column label="需求名称" align="center" prop="demandsName" />
      <el-table-column label="需求内容" align="center" prop="demandsContents" />
      <el-table-column label="解释需求来源，可以是书籍、视频等" align="center" prop="demandsSource" />
      <el-table-column label="需求紧急程度，划分三个档次" align="center" prop="demandsUrgency" />
      <el-table-column label="预计完成时间" align="center" prop="planTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际完成时间" align="center" prop="completeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['uranus:demands:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['uranus:demands:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改需求对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="需求名称" prop="demandsName">
          <el-input v-model="form.demandsName" placeholder="请输入需求名称" />
        </el-form-item>
        <el-form-item label="需求内容">
          <editor v-model="form.demandsContents" :min-height="192"/>
        </el-form-item>
        <el-form-item label="解释需求来源，可以是书籍、视频等" prop="demandsSource">
          <el-input v-model="form.demandsSource" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="需求紧急程度，划分三个档次" prop="demandsUrgency">
          <el-input v-model="form.demandsUrgency" placeholder="请输入需求紧急程度，划分三个档次" />
        </el-form-item>
        <el-form-item label="预计完成时间" prop="planTime">
          <el-date-picker clearable
            v-model="form.planTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预计完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际完成时间" prop="completeTime">
          <el-date-picker clearable
            v-model="form.completeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择实际完成时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDemands, getDemands, delDemands, addDemands, updateDemands } from "@/api/uranus/demands";

export default {
  name: "Demands",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 需求表格数据
      demandsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        demandsName: null,
        demandsContents: null,
        demandsSource: null,
        demandsUrgency: null,
        planTime: null,
        completeTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        demandsName: [
          { required: true, message: "需求名称不能为空", trigger: "blur" }
        ],
        demandsContents: [
          { required: true, message: "需求内容不能为空", trigger: "blur" }
        ],
        demandsSource: [
          { required: true, message: "解释需求来源，可以是书籍、视频等不能为空", trigger: "blur" }
        ],
        demandsUrgency: [
          { required: true, message: "需求紧急程度，划分三个档次不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        planTime: [
          { required: true, message: "预计完成时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询需求列表 */
    getList() {
      this.loading = true;
      listDemands(this.queryParams).then(response => {
        this.demandsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        demandsId: null,
        demandsName: null,
        demandsContents: null,
        demandsSource: null,
        demandsUrgency: null,
        createTime: null,
        planTime: null,
        completeTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.demandsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加需求";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const demandsId = row.demandsId || this.ids
      getDemands(demandsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改需求";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.demandsId != null) {
            updateDemands(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDemands(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const demandsIds = row.demandsId || this.ids;
      this.$modal.confirm('是否确认删除需求编号为"' + demandsIds + '"的数据项？').then(function() {
        return delDemands(demandsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('uranus/demands/export', {
        ...this.queryParams
      }, `demands_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
