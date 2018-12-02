var compMgmtFormPlugins = [
    {
      plugin : 'cropper',
      options : {
        key : 'compLogo',
        name : 'photo',
        compressWidth : 512,
        compressHeight : 512,
        cols : 1,
        rows : 4,
        validator : true
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'compName',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'textbox',
      options : {
        key : 'linkman',
        name : 'linkmanName',
        validator : true,
        maxlength : 128
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'linkmanCellphone',
        validator : 'required,cellphone',
        maxlength : 11
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'compKey',
        validator : true,
        maxlength : 16
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'token',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'abbreviation',
        validator : true,
        maxlength : 6
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
        name : 'telephone',
        maxlength : 16
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'website',
        validator : 'url',
        maxlength : 128,
        cols : 2
      }
    }, {
      plugin : 'textbox',
      options : {
        cols : 2,
        name : 'address',
        maxlength : 128
      }
    }, {
      plugin : 'textbox',
      options : {
        key : 'brief',
        name : 'description',
        cols : 2,
        rows : 4,
        maxlength : 1024
      }
    }, {
      plugin : 'hidden',
      options : {
        name : 'parentCode',
        value : 'ROOT'
      }
    }
];

LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'compMgmt',
} : {}), {
  i18nKey : 'compMgmt',
  $appendTo : true,
  cols : 5,
  url : '/SysComp/P',
  columns : [
      {
        text : 'compKey',
        width : 140,
        name : 'compKey'
      }, {
        text : 'compName',
        width : 200,
        name : 'compName'
      }, {
        text : 'abbreviation',
        width : 100,
        name : 'abbreviation'
      }, {
        text : 'token',
        width : 100,
        name : 'token'
      }, {
        text : 'linkman',
        width : 80,
        name : 'linkmanName'
      }, {
        text : 'telephone',
        width : 120,
        name : 'telephone'
      }, {
        text : 'linkmanCellphone',
        width : 120,
        name : 'linkmanCellphone'
      }, {
        text : 'email',
        width : 150,
        name : 'email'
      }, {
        text : 'website',
        width : 200,
        name : 'website'
      }, {
        text : 'address',
        width : null,
        name : 'address',
        textAlign : 'left'
      }
  ],
  pageable : true,
  toolsAdd : {
    saveUrl : '/SysComp/I',
    dialog : {
      size : {
        cols : 2,
        rows : 12
      }
    },
    form : {
      plugins : compMgmtFormPlugins
    }
  },
  toolsEdit : {
    saveUrl : '/SysComp/U',
    dialog : {
      size : {
        cols : 2,
        rows : 12
      }
    },
    form : {
      plugins : LK.UI.formUtils.newReadonlyPlugins(compMgmtFormPlugins, [
          'compName', 'compKey', 'token'
      ]),
      url : '/SysComp/O'
    }
  },
  toolsRemove : {
    saveUrl : '/SysComp/US'
  },
  toolsView : {
    dialog : {
      size : {
        cols : 2,
        rows : 12
      }
    },
    form : {
      plugins : compMgmtFormPlugins,
      url : '/SysComp/O'
    }
  },
  searchForm : [
      {
        plugin : 'textbox',
        options : {
          name : 'compName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          key : 'linkman',
          name : 'linkmanName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'linkmanCellphone',
          cls : 'fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'email',
          cls : 'fuzzy-left fuzzy-right'
        }
      }
  ]
}));
