
//用于获取url种name参数的值，不存在返回false
function getQueryVariable(name)
{
    name = name.replace(/[]/,"\[").replace(/[]/,"\[").replace(/[]/,"\\\]");
    var regexS = "[\\?&]"+name+"=([^&#]*)";
    var regex = new RegExp( regexS );
    var results = regex.exec(window.parent.location.href);
    if( results == null ) 
        return ""; 
    else {
        return decodeURI(results[1]);
    }
}



//问题页面换页
function changeQuestionPage(page, path){
    $.ajax({
        url: path,
        type: "post", 
        data:JSON.stringify(page),
        beforeSend: function (request) {
            request.setRequestHeader("token", userVO.token);
        },
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            page = data.data;
            console.info(page);
            updateList(page);
        }
    });
}

// //回复页面换页
// function changeQuestionPage(page, path){
//     $.ajax({
//         url: path,
//         type: "post", 
//         data:JSON.stringify(page),
//         contentType: 'application/json;charset=utf-8',
//         success: function (data) {
//             page = data.data;
//             console.info(page);
//             updateList(page);
//         }
//     });
// }

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
    $.ajax({
        url: "http://localhost:8888/question",
        type: "post", 
        data:JSON.stringify(question),
        beforeSend: function (request) {
            request.setRequestHeader("token", userVO.token);
        },
        contentType: 'application/json;charset=utf-8',
        success: function (data) {
            updateUser(data.data)
        }
    });
    //将输入内容清空
    $("#inputQue").innerHTML = "";
    $("#describeQue").innerHTML = "";
    location.replace(document.referrer);
}


//判断后台是否有临时板块（有则显示，无则隐藏）
function getBlock(){
    //获取临时板块的元素
    var tempBlock = document.getElementById("tempBlock");
    $.ajax({
        url: "http://localhost:8888/block",
        type: "get", 
        contentType: 'application/json;charset=utf-8',
        beforeSend: function (request) {
            request.setRequestHeader("token", userVO.token);
        },
        success: function (data) {
            if(data.code == 200){
                block = data.data;
                //有临时板块
                //展示临时板块按钮
                tempBlock.removeAttribute("style");
                var blockLink = document.createElement("a");
                blockLink.innerHTML = block.blockName;
                var href = "search.html?keyWord=" +  block.keyWord;
                blockLink.setAttribute("href",href);
                tempBlock.appendChild(blockLink);
                //console.info(block);
            }else if(data.code == 201){
                //没有临时板块
                //隐藏临时板块按钮
                tempBlock.setAttribute("style","display:none");
            }
        }
    });
}

//获取临时板块实体
function getBlockModel(){
    $.ajax({
        url: "http://localhost:8888/block",
        type: "get", 
        contentType: 'application/json;charset=utf-8',
        beforeSend: function (request) {
            request.setRequestHeader("token", userVO.token);
        },
        success: function (data) {
            if(data.code == 200){
                tempBlock = data.data;
                //有临时板块
            }else if(data.code == 201){
                //没有临时板块
                tempBlock = null;
            }
            //初始化界面
            initBlock();
        }
    });
}

//更新问题列表
function updateList(p){
    page = p;
    var list = page.questions;
    list = Array.prototype.slice.call(list);
    for(var i = 0; i < list.length; i++){
        //创建存放单个问题的blockquote
        var questionBlock = document.createElement("blockquote");
        //创建存放问题标题的a标签
        var title = document.createElement("a");
        title.innerHTML = "标题：" + list[i].title;//问题标题
        title.setAttribute("href", "questionIndex.html?id=" + list[i].question.id);//问题标题对应的详情页面
        //创建存放问题描述的p标签
        var content = document.createElement("small");
        content.innerHTML = "问题描述：" + list[i].content;//问题描述
        questionBlock.appendChild(title);
        questionBlock.appendChild(content);
        questionList.appendChild(questionBlock);
        //更新分页按钮
        updatePageButtons();
    }
}

//将用户信息保存到缓存中(时间为30分钟)
function updateUser(userVO){
    localStorage.setExpire("userVO",userVO,1000*60*30);
}



//搜索框搜素函数
function search(){
    //搜索框输入内容
    var searchContent = document.getElementById("searchContent");
    var content = $.trim($("#searchContent").val());
    window.location.href="search.html?keyWord=" + content;
}

//后台页面注销登录
function adminExit(){
    localStorage.setExpire("userVO",null,0);
    location.href = "../login.html";
}

//前台页面注销登录
function exit(){
    localStorage.setExpire("userVO",userVO,1);
    //alert("12")
    //window.location.href = "my";

}

function changePage(index){
    questionDiv.removeChild(questionList);
    questionList = document.createElement("div");
    questionDiv.insertBefore(questionList, pageButtons);
    questionList.setAttribute("id","quetionList");
    //pageButtons.removeAttribute("disabled");
    page.pageIndex = index;
    changeQuestionPage(page, path);
}

//上一页触发事件
function prePage(flag, index){
    if(flag == "1"){
        changePage(index);
    }else{
        alert("抱歉，已经是第一页了");
    }
}

//下一页触发事件
function nextPage(flag, index){
    if(flag == "1"){
        changePage(index);
    }else{
        alert("抱歉，已经是最后一页了");
    }
}

//更新分页按钮
function updatePageButtons(){
    questionDiv.removeChild(pageButtons);
    pageButtons = document.createElement("ul");
    pageButtons.className = "pagination pagination-lg";
    pageButtons.id = "pageButtons";
    questionDiv.insertBefore(pageButtons,divEnd);

    //首页
    var firstButton = document.createElement("li");
    var firstLink = document.createElement("a");
    firstLink.innerHTML = "首页";
    firstButton.appendChild(firstLink)
    pageButtons.appendChild(firstButton);
    firstButton.setAttribute("onclick","changePage(" + 1 + ");");

    //上一页
    var preButton = document.createElement("li");
    var preLink = document.createElement("a");
    preLink.innerHTML = "上一页";
    preButton.appendChild(preLink);
    pageButtons.append(preButton);
    preButton.setAttribute("id","previous");

    if(page.hasPrevious == true){//有上一页
        preButton.setAttribute("onclick", "prePage( 1, " + (page.pageIndex-1) + ");");
    }else{//没有上一页
        preButton.setAttribute("onclick", "prePage( 0, " + (page.pageIndex-1) + ");");
    }

    //分页页码列表
    var buttonList = page.buttonList;
    console.info(buttonList);

    for(var i = 0; i < buttonList.length; i++){
        var button_i = document.createElement("li")
        var link_i = document.createElement("a");
        link_i.innerHTML = buttonList[i];
        button_i.appendChild(link_i);
        pageButtons.appendChild(button_i);
        button_i.setAttribute("onclick", "changePage(" + buttonList[i] + ");")
        if(buttonList[i] == page.pageIndex){
            link_i.setAttribute("style","color:red");
        }
    }

    //下一页
    var nextButton = document.createElement("li");
    var nextLink = document.createElement("a");
    nextLink.innerHTML = "下一页";
    nextButton.appendChild(nextLink);
    pageButtons.append(nextButton);
    nextButton.setAttribute("id","next")
    nextButton.setAttribute("onclick", "nextPage(" + (page.pageIndex+1) + ");");

    if(page.hasNext == true){//有下一页
        nextButton.setAttribute("onclick", "nextPage( 1, " + (page.pageIndex+1) + ");");
    }else{//没有下一页
        nextButton.setAttribute("onclick", "nextPage( 0, " + (page.pageIndex+1) + ");");
    }

    //末页
    var lastButton = document.createElement("li");
    var lastLink = document.createElement("a");
    lastLink.innerHTML = "末页";
    lastButton.appendChild(lastLink)
    pageButtons.appendChild(lastButton);
    lastButton.setAttribute("onclick", "changePage(" + page.pageNum + ");")
}

var userVO;
//获取当前登录用户
userVO = localStorage.getExpire("userVO");
if(userVO != null){
    updateUser(userVO);
}

