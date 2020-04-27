
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

//将数据存储到缓存
Storage.prototype.setExpire=(key, value, expire) =>{
    let obj={
        data:value,
        time:Date.now(),
        expire:expire
    };
    localStorage.setItem(key,JSON.stringify(obj));
}

//从缓存中读取数据
Storage.prototype.getExpire= key =>{
    let val =localStorage.getItem(key);
    if(!val){
        return val;
    }
    val =JSON.parse(val);
        if(Date.now()-val.time>val.expire){
        localStorage.removeItem(key);
        return null;
    }
    return val.data;
}


function initInfo(userVO){//对个人信息进行赋值
    //姓名
    var nameDD = document.getElementById("infoName");
    nameDD.innerHTML = userVO.user.name;
    //账号（学号）
    var accountDD = document.getElementById("infoAccount");
    accountDD.innerHTML = userVO.user.account;
    //等级
    var levelDD = document.getElementById("infoLevel");
    levelDD.innerHTML = userVO.accountData.level;
    //积分
    var scoreDD = document.getElementById("infoScore");
    scoreDD.innerHTML = userVO.accountData.score;
    //提问数
    var quesNumDD = document.getElementById("infoQuesNum");
    quesNumDD.innerHTML = userVO.accountData.questionNum;
    //回复数
    var respNumDD = document.getElementById("infoRespNum");
    respNumDD.innerHTML = userVO.accountData.responseNum;
    //关注数
    var focusNumDD = document.getElementById("infoFocusNum");
    focusNumDD.innerHTML = userVO.accountData.focusNum;
}

//获取格式为yyyy-MM-dd HH-mm-ss格式的时间字符串
function getTimeString(date){
    var year = date.getFullYear();

    var month = date.getMonth() + 1;
    if(month < 10){
        month = "0" + month;
    }

    var strDate = date.getDate();
    if(strDate < 10){
        strDate = "0" + strDate;
    }

    var hour = date.getHours();
    if(hour < 10){
        hour = "0" + hour;
    }

    var min = date.getMinutes(); 
    if(min < 10){
        min = "0" + min;
    }
    
    var sec = date.getSeconds(); 
    if(sec < 10){
        sec = "0" + sec;
    }

    return "" + year + "-" + month + "-" + strDate + " " + hour + ":" + min + ":" + sec;
}

//创建问题
function postQuestion(){
    var title =  $.trim($("#inputQue").val());
    var content = $.trim($("#describeQue").val());
    //创建时间
    var time = new Date();
    var timeString = getTimeString(time);
    var question={
        "title":title,
        "content":content,
        "question":{
            "authorId":userVO.user.id,
            "createTime":timeString
        }
    }
    console.info(question);
    $.ajax({
        url: "http://localhost:8888/question",
        type: "post", 
        data:JSON.stringify(question),
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            console.info(data);
            alert("创建成功");
        }
    });
    //将输入内容清空
    $("#inputQue").innerHTML = "";
    $("#describeQue").innerHTML = "";
    location.replace(document.referrer);
}

var userVO;
//获取当前登录用户
userVO = localStorage.getExpire("userVO");

