

//用于获取url种variable参数的值，不存在返回false
function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}



//问题页面换页
function changeQuestionPage(page, path){
    $.ajax({
        url: path,
        type: "post", 
        data:JSON.stringify(page),
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            page = data.data;
            console.info(page);
            updateList(page);
        }
    });
}