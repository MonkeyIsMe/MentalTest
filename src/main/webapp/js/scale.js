/**
 * Created by CallMeDad on 2020/02/17.
 */

var row = 1;  //页数
var count; //总记录数
var rid,rname,pid,rarea;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"MentalTest/CountScale",
			{
	            scale_flag:"null",
	            flag_id:0,
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.Count;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);

	
});


$(function(){
    $.post(
        "MentalTest/QueryScalePageSize",
        {
            page:row,
            limit:25,
            scale_flag:"null",
            flag_id:0,
            
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.Array.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].ScaleId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].ScaleName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].ScaleNumber +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].ScaleUpdateTime  +"</td>");
			        $trTemp.append("<td>" + 
			        		'<a><span class="delete" style="cursor:pointer;" data-toggle="modal" data-target="#delchoice_Modal">删除</span></a>'
			        		 + '<a><span class="update" style="cursor:pointer;margin-left:18px" data-toggle="modal" data-target="#upchoice_Modal">修改</span></a>'
			        		 + '<a><span class="see" style="cursor:pointer;margin-left:18px" ></span>查看</a>'
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#ScaleList");
                }
        }
    );

});


function PrevPage(){	
	if(row == 1){
		alert("没有前一页了");
	}
	else{
		row--;
		$("#ScaleList").html("");
	    $.post(
	            "http://47.105.136.92/MentalTest/QueryScalePageSize",
	            {
	                page:row,
	                limit:25,
	                scale_flag:"null",
	                flag_id:0,
	                
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                console.log(data);
	                    for( var i = 0; i < data.Array.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].ScaleId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].ScaleName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].ScaleInfo +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].ScaleGuide  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].FkindName +"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#ScaleList");
	                    }
	            }
	        );
	}
}

function NextPage(){
	if(row == count){
		alert("没有后一页了");
	}
	else{
		row ++;
		$("#ScaleList").html("");
	    $.post(
	            "http://47.105.136.92/MentalTest/QueryScalePageSize",
	            {
	                page:row,
	                limit:25,
	                scale_flag:"null",
	                flag_id:0,
	                
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                console.log(data);
	                    for( var i = 0; i < data.Array.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data.Array[i].ScaleId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].ScaleName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].ScaleInfo +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data.Array[i].ScaleGuide  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data.Array[i].FkindName +"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#ScaleList");
	                    }
	            }
	        );
	}

}

