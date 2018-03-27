//初始化
window.onload = function() {
	$("#left").ligerTree({
		data : treeData,
		checkbox : false,
		slide : false,
		nodeWidth : 220,
		isExpand:false,
		onSelect : function(node) {
			setMethodInfo(node);
		}
	});
}

// 设置方法详情
function setMethodInfo(node) {
	if (node != null && node.data.treedataindex > 0) {
		var code=node.data.code;
		var ms=msData[code];
		$("#k1").html(code+"[sdk方法名:"+node.data.mName+"]");
		setFormatJson("#k2", ms.req);
		setFormatJson("#k3", ms.resp);
	}
}

// 格式化json
function setFormatJson(id, txt) {
	var options = {
		dom : id,
		imgCollapsed : "../http/jf/images/Collapsed.gif",
		imgExpanded : "../http/jf/images/Expanded.gif",
		quoteKeys : false
	};
	var jf = new JsonFormater(options);
	jf.doFormat(txt);
}