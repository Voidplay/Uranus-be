package com.ruoyi.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.domain.UranusTradeCrypto;
import com.ruoyi.domain.UranusTradeHistory;
import com.ruoyi.service.IUranusTradeCryptoService;
import com.ruoyi.service.IUranusTradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 虚拟货币交易Controller
 *
 * @author uranus
 * @date 2023-10-28
 */
@RestController
@RequestMapping("/uranus/crypto")
public class UranusTradeCryptoController extends BaseController {
    @Autowired
    private IUranusTradeCryptoService uranusTradeCryptoService;
    @Autowired
    private IUranusTradeHistoryService uranusTradeHistoryService;

    /**
     * 查询虚拟货币持仓列表，status状态不为已平仓
     */
    @GetMapping("/list")
    public TableDataInfo list(UranusTradeCrypto uranusTradeCrypto) {
        startPage();
        List<UranusTradeCrypto> list = uranusTradeCryptoService.selectUranusTradeCryptoOpenList(uranusTradeCrypto);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getDirection().equals("long")) {
                list.get(i).setDirection("做多");
            } else {
                list.get(i).setDirection("做空");
            }
            if (list.get(i).getStatus().equals("waiting")) {
                list.get(i).setStatus("挂单待成交");
            } else if (list.get(i).getStatus().equals("openPosition")) {
                list.get(i).setStatus("持仓中");
            }
        }
        return getDataTable(list);
    }

    @GetMapping("/closed-list")
    public TableDataInfo closedList(UranusTradeCrypto uranusTradeCrypto) {
        startPage();
        List<UranusTradeCrypto> list = uranusTradeCryptoService.selectUranusTradeCryptoList(uranusTradeCrypto);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getDirection().equals("long")) {
                list.get(i).setDirection("做多");
            } else {
                list.get(i).setDirection("做空");
            }
            if (list.get(i).getStatus().equals("waiting")) {
                list.remove(i);
            } else if (list.get(i).getStatus().equals("openPosition")) {
                list.remove(i);
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出虚拟货币交易列表
     */
    @Log(title = "虚拟货币交易", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UranusTradeCrypto uranusTradeCrypto) {
        List<UranusTradeCrypto> list = uranusTradeCryptoService.selectUranusTradeCryptoList(uranusTradeCrypto);
        ExcelUtil<UranusTradeCrypto> util = new ExcelUtil<UranusTradeCrypto>(UranusTradeCrypto.class);
        util.exportExcel(response, list, "虚拟货币交易数据");
    }

    /**
     * 获取虚拟货币交易详细信息
     */
    @GetMapping(value = "/{tradeCryptoId}")
    public AjaxResult getInfo(@PathVariable("tradeCryptoId") Long tradeCryptoId) {
        return success(uranusTradeCryptoService.selectUranusTradeCryptoByTradeCryptoId(tradeCryptoId));
    }

    /**
     * 新增虚拟货币交易
     */
    @Log(title = "虚拟货币交易", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UranusTradeCrypto uranusTradeCrypto) {
        //设置交易编号
        uranusTradeCrypto.setReviewId(uranusTradeCryptoService.generateReviewId());
        //设置止损价位
        uranusTradeCrypto.setStopLossPrice(uranusTradeCryptoService.generateStopLossPrice(uranusTradeCrypto));

        int rows = uranusTradeCryptoService.insertUranusTradeCrypto(uranusTradeCrypto);
        String msg = uranusTradeCryptoService.generateReturnMsg(uranusTradeCrypto);
        return rows > 0 ? AjaxResult.success(msg) : AjaxResult.error();

    }

    /**
     * 修改虚拟货币交易
     */
    @Log(title = "虚拟货币交易", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UranusTradeCrypto uranusTradeCrypto) {
        if (uranusTradeCrypto.getStatus().equals("openPosition") && uranusTradeCrypto.getEntryPrice() == null) {
            return AjaxResult.warn("操作失败,持仓状态下需填写入场价格");
        }if (uranusTradeCrypto.getStatus().equals("closePosition")){
            if (uranusTradeCrypto.getEntryPrice() == null){
                return AjaxResult.warn("操作失败,平仓前请先填写入场价格");
            } else if (uranusTradeCrypto.getEntryDate() == null) {
                return AjaxResult.warn("操作失败,平仓前请先填写入场时间");
            }
            uranusTradeCrypto = uranusTradeCryptoService.cryptoClosePosition(uranusTradeCrypto);
        }

        int rows = uranusTradeCryptoService.updateUranusTradeCrypto(uranusTradeCrypto);
        //平仓时插入交易历史表
        if (rows > 0 && uranusTradeCrypto.getStatus().equals("closePosition")){
            UranusTradeHistory uranusTradeHistory = new UranusTradeHistory();
            uranusTradeHistory.setTradeSubjectName("crypto");
            uranusTradeHistory.setTradeReviewid(uranusTradeCrypto.getReviewId());
            uranusTradeHistory.setTradeWinorlose(uranusTradeCrypto.getWinorlose());
            uranusTradeHistory.setEntryDate(uranusTradeCrypto.getEntryDate());
            uranusTradeHistory.setLiquidateTime(uranusTradeCrypto.getLiquidateDate());
            uranusTradeHistory.setBalanceChanges(uranusTradeCrypto.getBalanceChanges());
            uranusTradeHistoryService.insertUranusTradeHistory(uranusTradeHistory);
        }
        return toAjax(rows);
    }

    /**
     * 删除虚拟货币交易
     */
    @Log(title = "虚拟货币交易", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tradeCryptoIds}")
    public AjaxResult remove(@PathVariable Long[] tradeCryptoIds) {
        return toAjax(uranusTradeCryptoService.deleteUranusTradeCryptoByTradeCryptoIds(tradeCryptoIds));
    }
}
