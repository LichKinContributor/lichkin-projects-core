$.LKExtendI18N({
  'adminMgmt' : {
    'title' : '账号管理',

    'grid' : {
      'title' : '账号列表',

      'columns' : {
        'email' : '账号',
        'userName' : '姓名',
        'roleName' : '角色',
        'photo' : '头像',
        'level' : '等级',
        'usingStatus' : '在用状态',
        'gender' : '性别',
      },

      'add' : '新增账号',
      'edit' : '编辑账号',
      'remove' : '删除账号',
      'resetPwd' : '重置密码',
      'unlock' : '解锁账号',
      'lock' : '锁定账号',
      'view' : '查看账号',

      'confirm' : {
        'remove' : '数据删除后将不能恢复，确认删除么？',
        'resetPwd' : '确认重置密码么？',
        'unlock' : '账号解锁后可正常使用，确认解锁么？',
        'lock' : '账号锁定后将不能使用，确认锁定么？',
      },

      'lock can only be performed when it is USING!' : '只有在用状态才可以进行锁定操作！',
      'unlock can only be performed when it is LOCKED!' : '只有锁定状态才可以进行解锁操作！',
    }
  }
});
