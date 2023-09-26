package com.ruoyi.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.domain.UranusDemands;
import com.ruoyi.service.IUranusDemandsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 需求Controller
 * 
 * @author uranus
 * @date 2023-09-10
 */
@RestController
@RequestMapping("/uranus/demands")
public class UranusDemandsController extends BaseController
{
    @Autowired
    private IUranusDemandsService uranusDemandsService;

    /**
     * 查询需求列表
     */
    @PreAuthorize("@ss.hasPermi('uranus:demands:list')")
    @GetMapping("/list")
    public TableDataInfo list(UranusDemands uranusDemands)
    {
        startPage();
        List<UranusDemands> list = uranusDemandsService.selectUranusDemandsList(uranusDemands);
        return getDataTable(list);
    }

    /**
     * 导出需求列表
     */
    @PreAuthorize("@ss.hasPermi('uranus:demands:export')")
    @Log(title = "需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UranusDemands uranusDemands)
    {
        List<UranusDemands> list = uranusDemandsService.selectUranusDemandsList(uranusDemands);
        ExcelUtil<UranusDemands> util = new ExcelUtil<UranusDemands>(UranusDemands.class);
        util.exportExcel(response, list, "需求数据");
    }

    /**
     * 获取需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('uranus:demands:query')")
    @GetMapping(value = "/{demandsId}")
    public AjaxResult getInfo(@PathVariable("demandsId") Long demandsId)
    {
        return success(uranusDemandsService.selectUranusDemandsByDemandsId(demandsId));
    }

    /**
     * 新增需求
     */
    @PreAuthorize("@ss.hasPermi('uranus:demands:add')")
    @Log(title = "需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UranusDemands uranusDemands)
    {
        return toAjax(uranusDemandsService.insertUranusDemands(uranusDemands));
    }

    /**
     * 修改需求
     */
    @PreAuthorize("@ss.hasPermi('uranus:demands:edit')")
    @Log(title = "需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UranusDemands uranusDemands)
    {
        return toAjax(uranusDemandsService.updateUranusDemands(uranusDemands));
    }

    /**
     * 删除需求
     */
    @PreAuthorize("@ss.hasPermi('uranus:demands:remove')")
    @Log(title = "需求", businessType = BusinessType.DELETE)
	@DeleteMapping("/{demandsIds}")
    public AjaxResult remove(@PathVariable Long[] demandsIds)
    {
        return toAjax(uranusDemandsService.deleteUranusDemandsByDemandsIds(demandsIds));
    }
}
