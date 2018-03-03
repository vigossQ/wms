<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="ui_tb_h30">
    <div class="ui_flt" style="height: 30px; line-height: 30px;">
        共有
        <span class="ui_txt_bold04">${result.totalCount}</span>
        条记录，当前第
        <span class="ui_txt_bold04">${result.currentPage}/${result.endPage}</span>
        页
    </div>
    <div class="ui_frt">
        <input type="button" value="首页" data-page="1"
               class="ui_input_btn01 btn_page"/>
        <input type="button" value="上一页" data-page="${result.prevPage}"
               class="ui_input_btn01 btn_page"/>
        <input type="button" value="下一页" data-page="${result.nextPage}"
               class="ui_input_btn01 btn_page"/>
        <input type="button" value="尾页" data-page="${result.endPage}"
               class="ui_input_btn01 btn_page"/>
        <select name="pageSize" class="ui_select02" data-pagesize="${qo.pageSize}">
            <option>3</option>
            <option>6</option>
            <option>9</option>
        </select>
        转到第
        <input id="currentPage" type="text" name="currentPage"
               value="${result.currentPage}" class="ui_input_txt01" />
        页
        <input type="submit" class="ui_input_btn01" value="跳转" />
    </div>
</div>