package com.ruoyi.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.domain.UranusTradeCrypto;
import com.ruoyi.service.IUranusTradeCryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

/**
 * 虚拟货币交易Controller
 * 
 * @author uranus
 * @date 2023-10-28
 */
@RestController
@RequestMapping("/uranus/crypto")
public class UranusTradeCryptoController extends BaseController
{
    @Autowired
    private IUranusTradeCryptoService uranusTradeCryptoService;

    /**
     * 查询虚拟货币持仓列表，status状态不为已平仓
     */
    @GetMapping("/list")
    public TableDataInfo list(UranusTradeCrypto uranusTradeCrypto)
    {
        startPage();
        List<UranusTradeCrypto> list = uranusTradeCryptoService.selectUranusTradeCryptoList(uranusTradeCrypto);
        for (UranusTradeCrypto ut:list
        ) {
            if (ut.getStatus().equals("waiting")){
                ut.setStatus("挂单待成交");
            } else if (ut.getStatus().equals("openPosition")) {
                ut.setStatus("持仓中");
            }
            if (ut.getDirection().equals("long")){
                ut.setDirection("做多");
            }else {
                ut.setDirection("做空");
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出虚拟货币交易列表
     */
    @Log(title = "虚拟货币交易", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UranusTradeCrypto uranusTradeCrypto)
    {
        List<UranusTradeCrypto> list = uranusTradeCryptoService.selectUranusTradeCryptoList(uranusTradeCrypto);
        ExcelUtil<UranusTradeCrypto> util = new ExcelUtil<UranusTradeCrypto>(UranusTradeCrypto.class);
        util.exportExcel(response, list, "虚拟货币交易数据");
    }

    /**
     * 获取虚拟货币交易详细信息
     */
    @GetMapping(value = "/{tradeCryptoId}")
    public AjaxResult getInfo(@PathVariable("tradeCryptoId") Long tradeCryptoId)
    {
        return success(uranusTradeCryptoService.selectUranusTradeCryptoByTradeCryptoId(tradeCryptoId));
    }

    /**
     * 新增虚拟货币交易
     */
    @Log(title = "虚拟货币交易", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UranusTradeCrypto uranusTradeCrypto)
    {

        uranusTradeCrypto.setReviewId(uranusTradeCryptoService.generateReviewId());

        return toAjax(uranusTradeCryptoService.insertUranusTradeCrypto(uranusTradeCrypto));
    }

    /**
     * 修改虚拟货币交易
     */
    @Log(title = "虚拟货币交易", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UranusTradeCrypto uranusTradeCrypto)
    {
        if (uranusTradeCrypto.getStatus().equals("openPosition") && uranusTradeCrypto.getEntryPrice() == null){
            return AjaxResult.warn("操作失败,持仓状态下需填写入场价格");
        }
        return toAjax(uranusTradeCryptoService.updateUranusTradeCrypto(uranusTradeCrypto));
    }

    /**
     * 删除虚拟货币交易
     */
    @Log(title = "虚拟货币交易", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tradeCryptoIds}")
    public AjaxResult remove(@PathVariable Long[] tradeCryptoIds)
    {
        return toAjax(uranusTradeCryptoService.deleteUranusTradeCryptoByTradeCryptoIds(tradeCryptoIds));
    }
}
