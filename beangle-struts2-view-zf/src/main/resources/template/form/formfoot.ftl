[#ftl]
[#--
<li${tag.parameterString} class="foot"><div style="width:200px;margin:0 auto;">${tag.body}</div></li>
--]
[#--
<tr>
    <td class="td_style_2 formfoot" colspan="2" align="center">${tag.body!}</td>
</tr>
--]
<tfoot>
      <tr>
        <td colspan="2"><div class="bz">"<span class="red">*</span>"为必填项</div>
          <div class="btn">
            ${tag.body!}
          </div></td>
      </tr>
</tfoot>