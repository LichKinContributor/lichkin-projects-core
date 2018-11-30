var employeeMgmtFormPlugins = [
    {
      plugin : 'textbox',
      options : {
        name : 'jobNumber',
        validator : true,
        maxlength : 8
      }
    }, {
      plugin : 'datepicker',
      options : {
        name : 'entryDate',
        validator : true,
        minDate: '1986-11-01'
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'userName',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'jobTitle',
        param : {
          categoryCode : 'JOB_TITLE'
        }
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'gender',
        param : {
          categoryCode : 'GENDER',
          includes : [
              'MALE', 'FEMALE'
          ].join(LK.SPLITOR)
        },
        validator : true,
        cancelable : false
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'cellphone',
        validator : 'required,cellphone',
        maxlength : 11
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'email',
        validator : 'email',
        maxlength : 128
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'userCard',
        validator : 'IDCard',
        maxlength : 18
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'birthplace',
        maxlength : 64
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'education',
        param : {
          categoryCode : 'EDUCATION'
        }
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'degree',
        param : {
          categoryCode : 'DEGREE'
        }
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'maritalStatus',
        param : {
          categoryCode : 'MARITAL_STATUS'
        }
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'nation',
        param : {
          categoryCode : 'NATION'
        }
      }
    }, {
      plugin : 'tree',
      options : {
        key : 'deptName',
        name : 'deptId',
        validator : true,
        checkbox : false,
        multiSelect : false,
        cancelable : false,
        i18nText : false,
        url : '/SysDept/S'
      }
    }
];

LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'employeeMgmt',
} : {}), {
  i18nKey : 'employeeMgmt',
  $appendTo : true,
  cols : 4,
  url : '/SysEmployee/P',
  columns : [
      {
        text : 'jobNumber',
        width : 100,
        name : 'jobNumber'
      }, {
        text : 'deptName',
        width : 220,
        name : 'fullName',
        textAlign : 'left'
      }, {
        text : 'userName',
        width : 160,
        name : 'userName'
      }, {
        text : 'gender',
        width : 60,
        name : 'gender'
      }, {
        text : 'cellphone',
        width : 100,
        name : 'cellphone'
      }, {
        text : 'userCard',
        width : 150,
        name : 'userCard'
      }, {
        text : 'email',
        width : 180,
        name : 'email'
      }, {
        text : 'education',
        width : 80,
        name : 'education'
      }, {
        text : 'degree',
        width : 80,
        name : 'degree'
      }, {
        text : 'maritalStatus',
        width : 100,
        name : 'maritalStatus'
      }, {
        text : 'nation',
        width : 80,
        name : 'nation'
      }, {
        text : 'entryDate',
        width : 80,
        name : 'entryDate'
      }, {
        text : 'jobTitle',
        width : 120,
        name : 'jobTitle'
      }, {
        text : 'usingStatus',
        width : 80,
        name : 'usingStatus'
      }
  ],
  pageable : true,
  toolsAdd : {
    saveUrl : '/SysEmployee/I',
    dialog : {
      size : {
        cols : 2,
        rows : 17
      }
    },
    form : {
      plugins : employeeMgmtFormPlugins
    }
  },
  toolsEdit : {
    saveUrl : '/SysEmployee/U',
    dialog : {
      size : {
        cols : 2,
        rows : 17
      }
    },
    form : {
      plugins : LK.UI.formUtils.newReadonlyPlugins(employeeMgmtFormPlugins, [
          'jobNumber', 'entryDate'
      ]),
      url : '/SysEmployee/O'
    },
    beforeClick : function($button, $datagrid, $selecteds, selectedDatas, value, i18nKey) {
      if (selectedDatas.usingStatusDictCode == 'DEPRECATED') {
        LK.alert(i18nKey + 'deprecated employee can not be edit');
        return false;
      }
      return true;
    }
  },
  toolsRemove : {
    saveUrl : '/SysEmployee/US'
  },
  toolsView : {
    dialog : {
      size : {
        cols : 2,
        rows : 17
      }
    },
    form : {
      plugins : employeeMgmtFormPlugins,
      url : '/SysEmployee/O'
    }
  },
  searchForm : [
      {
        plugin : 'textbox',
        options : {
          name : 'userName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'userCard',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'cellphone',
          cls : 'fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'email',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'droplist',
        options : {
          name : 'gender',
          param : {
            categoryCode : 'GENDER',
            includes : [
                'MALE', 'FEMALE'
            ].join(LK.SPLITOR)
          }
        }
      }, {
        plugin : 'droplist',
        options : {
          name : 'usingStatus',
          param : {
            categoryCode : 'EMPLOYEE_USING_STATUS',
            includes : 'USING' + LK.SPLITOR + 'DEPRECATED'
          },
          value : 'USING',
          cancelable : false
        }
      }
  ]
}));
