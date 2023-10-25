package com.ruoyi.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.domain.UranusPosition;
import com.ruoyi.service.IUranusPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 持仓Controller
 * 
 * @author uranus
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/uranus/position")
public class UranusPositionController extends BaseController
{
    @Autowired
    private IUranusPositionService uranusPositionService;

    /**
     * 查询持仓列表
     */
    @GetMapping("/list")
    public TableDataInfo list(UranusPosition uranusPosition)
    {
        startPage();
        List<UranusPosition> list = uranusPositionService.selectUranusPositionList(uranusPosition);
        return getDataTable(list);
    }

    /**
     * 导出持仓列表
     */
    @Log(title = "持仓", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UranusPosition uranusPosition)
    {
        List<UranusPosition> list = uranusPositionService.selectUranusPositionList(uranusPosition);
        ExcelUtil<UranusPosition> util = new ExcelUtil<UranusPosition>(UranusPosition.class);
        util.exportExcel(response, list, "持仓数据");
    }

    /**
     * 获取持仓详细信息
     */
    @GetMapping(value = "/{positionId}")
    public AjaxResult getInfo(@PathVariable("positionId") Long positionId)
    {
        return success(uranusPositionService.selectUranusPositionByPositionId(positionId));
    }

    /**
     * 新增持仓
     */
    @Log(title = "持仓", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UranusPosition uranusPosition)
    {
        return toAjax(uranusPositionService.insertUranusPosition(uranusPosition));
    }

    /**
     * 修改持仓
     */
    @Log(title = "持仓", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UranusPosition uranusPosition)
    {
        return toAjax(uranusPositionService.updateUranusPosition(uranusPosition));
    }

    /**
     * 删除持仓
     */
    @Log(title = "持仓", businessType = BusinessType.DELETE)
	@DeleteMapping("/{positionIds}")
    public AjaxResult remove(@PathVariable Long[] positionIds)
    {
        return toAjax(uranusPositionService.deleteUranusPositionByPositionIds(positionIds));
    }
}
