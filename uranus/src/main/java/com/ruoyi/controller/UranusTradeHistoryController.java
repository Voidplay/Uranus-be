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
import com.ruoyi.domain.UranusTradeHistory;
import com.ruoyi.service.IUranusTradeHistoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交易历史Controller
 * 
 * @author uranus
 * @date 2023-11-12
 */
@RestController
@RequestMapping("/uranus/history")
public class UranusTradeHistoryController extends BaseController
{
    @Autowired
    private IUranusTradeHistoryService uranusTradeHistoryService;

    /**
     * 查询交易历史列表
     */
    @PreAuthorize("@ss.hasPermi('uranus:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(UranusTradeHistory uranusTradeHistory)
    {
        startPage();
        List<UranusTradeHistory> list = uranusTradeHistoryService.selectUranusTradeHistoryList(uranusTradeHistory);
        return getDataTable(list);
    }

    /**
     * 导出交易历史列表
     */
    @PreAuthorize("@ss.hasPermi('uranus:history:export')")
    @Log(title = "交易历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UranusTradeHistory uranusTradeHistory)
    {
        List<UranusTradeHistory> list = uranusTradeHistoryService.selectUranusTradeHistoryList(uranusTradeHistory);
        ExcelUtil<UranusTradeHistory> util = new ExcelUtil<UranusTradeHistory>(UranusTradeHistory.class);
        util.exportExcel(response, list, "交易历史数据");
    }

    /**
     * 获取交易历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('uranus:history:query')")
    @GetMapping(value = "/{tradeHistoryId}")
    public AjaxResult getInfo(@PathVariable("tradeHistoryId") Long tradeHistoryId)
    {
        return success(uranusTradeHistoryService.selectUranusTradeHistoryByTradeHistoryId(tradeHistoryId));
    }

    /**
     * 新增交易历史
     */
    @PreAuthorize("@ss.hasPermi('uranus:history:add')")
    @Log(title = "交易历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UranusTradeHistory uranusTradeHistory)
    {
        return toAjax(uranusTradeHistoryService.insertUranusTradeHistory(uranusTradeHistory));
    }

    /**
     * 修改交易历史
     */
    @PreAuthorize("@ss.hasPermi('uranus:history:edit')")
    @Log(title = "交易历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UranusTradeHistory uranusTradeHistory)
    {
        return toAjax(uranusTradeHistoryService.updateUranusTradeHistory(uranusTradeHistory));
    }

    /**
     * 删除交易历史
     */
    @PreAuthorize("@ss.hasPermi('uranus:history:remove')")
    @Log(title = "交易历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tradeHistoryIds}")
    public AjaxResult remove(@PathVariable Long[] tradeHistoryIds)
    {
        return toAjax(uranusTradeHistoryService.deleteUranusTradeHistoryByTradeHistoryIds(tradeHistoryIds));
    }
}
