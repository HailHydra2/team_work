

jQuery(function ($) {
  var grid_selector = "#grid-table";
  var pager_selector = "#grid-pager";

  jQuery(grid_selector).jqGrid({
    url: "http://localhost:8888/rewards",
    loadonce: true,
    mtype: "get",
    //data: grid_data,
    datatype: "json",
    //    mtype:"POST",
    //datatype:"local",
    height: 250,
    colNames: ['编号', '用户学号', '姓名', '申请时间', '申请类别', '申请数量'],
    colModel: [
      {
        name: 'reward.id',
        index: 'reward.id',
        width: 100,
        //  sorttype: "int",
        editable: true
      },
      {
        name: 'account',
        index: 'account',
        //  sorttype: "int",
        width: 100,
        editable: true,
      },
      {
        name: 'name',
        index: 'name',
        width: 100,
        //  sorttype: "int",
        editable: true
      },
      {
        name: 'reward.applyTime',
        index: 'reward.applyTime',
        width: 200,
      },
      {
        name: 'reward.type',
        index: 'reward.type',
        width: 100,
        //  sorttype: "int",
        editable: true
      },
      {
        name: 'reward.rewardNum',
        index: 'reward.rewardNum',
        width: 100,
        sorttype: "int",
        editable: true
      }
    ],

    width: 80,
    fixed: true,
    sortname: 'id',
    viewrecords: true,
    rowNum: 10,
    rowList: [10, 20, 30],
    pager: pager_selector,
    altRows: true,
    multiselect: true,


    loadComplete: function () {
      var table = this;

      setTimeout(function () {
        updateActionIcons(table);
        updatePagerIcons(table);
        enableTooltips(table);
      }, 0);
    },
    //editurl: 'server.php', //nothing is saved
    editurl: $path_base, //nothing is saved
    caption: "积分信息操作",
    autowidth: true
  });

  //enable search/filter toolbar
  //jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})

  //switch element when editing inline
  function aceSwitch(cellvalue, options, cell) {
    setTimeout(function () {
      $(cell).find('input[type=checkbox]')
        .wrap('<label class="inline" />')
        .addClass('ace ace-switch ace-switch-5')
        .after('<span class="lbl"></span>');
    }, 0);
  }

  //enable datepicker
  /*function pickDate( cellvalue, options, cell ) {
    setTimeout(function(){
      $(cell) .find('input[type=text]')
          .datepicker({format:'yyyy-mm-dd' , autoclose:true});
    }, 0);
  }

*/
  //navButtons
  jQuery(grid_selector).jqGrid('navGrid', pager_selector, { //navbar options
    edit: false,
    editicon: 'icon-pencil blue',
    add: false,
    addicon: 'icon-plus-sign purple',
    del: false,
    delicon: 'icon-trash red',
    search: true,
    searchicon: 'icon-search orange',
    refresh: true,
    refreshicon: 'icon-refresh green',
    view: true,
    viewicon: 'icon-zoom-in grey',
  }, {

    //查询的表格设置
    //edit record form
    closeAfterEdit: true,
    recreateForm: true,
    beforeShowForm: function (e) {
      var form = $(e[0]);
      form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
      style_edit_form(form);
    }
  },
    {
      //增加行内容设置
      //new record form
      closeAfterAdd: true,
      recreateForm: true,
      viewPagerButtons: false,
      //设置新增表格样式
      beforeShowForm: function (e) {
        var form = $(e[0]);
        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        style_edit_form(form);
      },
      url: 'service-add.php',
      // onSubmit: function (params, postdata) {
      //   var submitButton = document.getElementById("sData");
      //   submitButton.onclick = function () {
      //     alert("你点击了按钮哦！");
      //   }
      //   var value = {
      //     "id": (document.getElementById("id")),
      //   };
      //   Service.appendData(value).then(function (data) {
      //     console.log(data);
      //     $(this).jqGrid().trigger('reloadGrid');
      //   });
      //   return true;
      // },
      afterSubmit: function (response, postdata) {
        if (response.responseText != "") {
          $(this).jqGrid('setGridParam', {
            datatype: 'json'
          }).trigger('reloadGrid');
          alert(response.responseText);
          return [true, response.responseText]
        }
      }
    },
    {
      //delete record form
      delData: {
        delId: function () {
          var sel_id = [];
          sel_id = $(grid_selector).jqGrid('getGridParam',
            'selarrrow');
          var value = '';
          for (var i = 0; i < sel_id.length; i++) {
            value = value + ',' + $(grid_selector).jqGrid('getCell',
              sel_id[i], 'id');
          }
          if (value.charAt(0) == ',') {
            value = value.substr(1);
          }
          Service.delete(value).then(function (data) {
            console.log(data);
            $(this).jqGrid().trigger('reloadGrid');
          });
          return value;
        }
      },
      recreateForm: true,
      beforeShowForm: function (e) {
        var form = $(e[0]);
        if (form.data('styled')) return false;
        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        style_delete_form(form);
        form.data('styled', true);
      },
      /*onClick : function(e) {
        alert("1");
      }*/
      closeOnEscape: true,
      closeAfterDelete: true,
      reloadAfterSubmit: true,
      drag: true,
    },
    {
      //search form
      recreateForm: true,
      afterShowSearch: function (e) {
        var form = $(e[0]);
        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
        style_search_form(form);
      },
      afterRedraw: function () {
        style_search_filters($(this));
      },
      multipleSearch: true,

      //  multipleGroup:true,
      // showQuery: true

    }, {
    //view record form
    recreateForm: true,
    beforeShowForm: function (e) {
      var form = $(e[0]);
      form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
    }
  })

  function style_edit_form(form) {
    //enable datepicker on "sdate" field and switches for "stock" field
    /*   form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
         .end().find('input[name=stock]')
             .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');
   */
    //update buttons classes
    var buttons = form.next().find('.EditButton .fm-button');
    buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
    buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
    buttons.eq(1).prepend('<i class="icon-remove"></i>')

    buttons = form.next().find('.navButton a');
    buttons.find('.ui-icon').remove();
    buttons.eq(0).append('<i class="icon-chevron-left"></i>');
    buttons.eq(1).append('<i class="icon-chevron-right"></i>');
  }

  //设置点击删除的按钮
  function style_delete_form(form) {
    var buttons = form.next().find('.EditButton .fm-button');
    buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove(); //ui-icon, s-icon
    buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
    buttons.eq(1).prepend('<i class="icon-remove"></i>')
  }

  //查询表格
  function style_search_filters(form) {
    form.find('.delete-rule').val('X');
    form.find('.add-rule').addClass('btn btn-xs btn-primary');
    form.find('.add-group').addClass('btn btn-xs btn-success');
    form.find('.delete-group').addClass('btn btn-xs btn-danger');
  }

  //设置查询的样式
  function style_search_form(form) {
    var dialog = form.closest('.ui-jqdialog');
    var buttons = dialog.find('.EditTable')
    buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
    buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
    buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
  }

  function beforeDeleteCallback(e) {
    var form = $(e[0]);
    if (form.data('styled')) return false;
    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
    style_delete_form(form);
    form.data('styled', true);
  }

  function beforeEditCallback(e) {
    var form = $(e[0]);
    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
    style_edit_form(form);
  }


  //it causes some flicker when reloading or navigating grid
  //it may be possible to have some custom formatter to do this as the grid is being created to prevent this
  //or go back to default browser checkbox styles for the grid
  function styleCheckbox(table) {
    /**
      $(table).find('input:checkbox').addClass('ace')
      .wrap('<label />')
      .after('<span class="lbl align-top" />')


      $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
      .find('input.cbox[type=checkbox]').addClass('ace')
      .wrap('<label />').after('<span class="lbl align-top" />');
    */
  }


  //unlike navButtons icons, action icons in rows seem to be hard-coded
  //you can change them like this in here if you want
  function updateActionIcons(table) {

    /*	var replacement =
      {
        'ui-icon-pencil' : 'icon-pencil blue',
        'ui-icon-trash' : 'icon-trash red',
        'ui-icon-disk' : 'icon-ok green',
        'ui-icon-cancel' : 'icon-remove red'
      };
      $(table).find('.ui-pg-div span.ui-icon').each(function(){
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
      })*/

  }

  //换页设置
  //replace icons with FontAwesome icons like above
  function updatePagerIcons(table) {
    var replacement = {
      'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
      'ui-icon-seek-prev': 'icon-angle-left bigger-140',
      'ui-icon-seek-next': 'icon-angle-right bigger-140',
      'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
    };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
      var icon = $(this);
      var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

      if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
    })
  }

  function enableTooltips(table) {
    $('.navtable .ui-pg-button').tooltip({ container: 'body' });
    $(table).find('.ui-pg-div').tooltip({ container: 'body' });
  }

  Date.prototype.Format = function (fmt) {
    var o = {
      "M+": this.getMonth() + 1, //月份 
      "d+": this.getDate(), //日 
      "h+": this.getHours(), //小时 
      "m+": this.getMinutes(), //分 
      "s+": this.getSeconds(), //秒 
      "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
      "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
      if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  }
  //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
});