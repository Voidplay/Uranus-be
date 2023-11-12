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
import com.ruoyi.domain.UranusDesign;
import com.ruoyi.service.IUranusDesignService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 需求Controller
 * 
 * @author uranus
 * @date 2023-09-24
 */
@RestController
@RequestMapping("/uranus/design")
public class UranusDesignController extends BaseController
{
    @Autowired
    private IUranusDesignService uranusDesignService;

    /**
     * 查询需求列表
     */
    @PreAuthorize("@ss.hasPermi('uranus:design:list')")
    @GetMapping("/list")
    public TableDataInfo list(UranusDesign uranusDesign)
    {
        startPage();
        List<UranusDesign> list = uranusDesignService.selectUranusDesignList(uranusDesign);
        return getDataTable(list);
    }

    /**
     * 导出需求列表
     */
    @PreAuthorize("@ss.hasPermi('uranus:design:export')")
    @Log(title = "需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UranusDesign uranusDesign)
    {
        List<UranusDesign> list = uranusDesignService.selectUranusDesignList(uranusDesign);
        ExcelUtil<UranusDesign> util = new ExcelUtil<UranusDesign>(UranusDesign.class);
        util.exportExcel(response, list, "需求数据");
    }

    /**
     * 获取需求详细信息
     */
    @PreAuthorize("@ss.hasPermi('uranus:design:query')")
    @GetMapping(value = "/{designId}")
    public AjaxResult getInfo(@PathVariable("designId") Long designId)
    {
        return success(uranusDesignService.selectUranusDesignByDesignId(designId));
    }

    /**
     * 新增需求
     */
    @PreAuthorize("@ss.hasPermi('uranus:design:add')")
    @Log(title = "需求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UranusDesign uranusDesign)
    {
        return toAjax(uranusDesignService.insertUranusDesign(uranusDesign));
    }

    /**
     * 修改需求
     */
    @PreAuthorize("@ss.hasPermi('uranus:design:edit')")
    @Log(title = "需求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UranusDesign uranusDesign)
    {
        return toAjax(uranusDesignService.updateUranusDesign(uranusDesign));
    }

    /**
     * 删除需求
     */
    @PreAuthorize("@ss.hasPermi('uranus:design:remove')")
    @Log(title = "需求", businessType = BusinessType.DELETE)
	@DeleteMapping("/{designIds}")
    public AjaxResult remove(@PathVariable Long[] designIds)
    {
        return toAjax(uranusDesignService.deleteUranusDesignByDesignIds(designIds));
    }
}
