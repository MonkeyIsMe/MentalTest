function InitProblemTable(){
    $.post(
            "MentalTest/QueryProblemByScale",
            {
            	scale_id:1
            },
            function(data) {
                var data = JSON.parse(data);
                console.log(data);
                    for( var i = 0; i < data.Array.length; i++ ) {
                        //动态创建一个tr行标签,并且转换成jQuery对象
                        var $trTemp = $("<tr ></tr>");
    			        $trTemp.append("<td class=pro style=" + "text-align:center"  + ">"+  data.Array[i].problemName +"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#ProbelmList");
                    }
            }
        );
}

InitProblemTable();