package phalaenopsis.common.entity;

public enum PermissionType {
            /// <summary>
			/// 模块（菜单）权限
			/// </summary>
			Module("1"),
			/// <summary>
			/// 页面权限
			/// </summary>
			Page("2"),
			/// <summary>
			/// 操作（按钮）权限
			/// </summary>
			Operation("3");
    private final String value;
	private PermissionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
