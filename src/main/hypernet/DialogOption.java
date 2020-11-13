package hypernet;

public enum DialogOption {

    INIT("返回"), EXIT("完成"), STAFF("检索雇员"), CARGO("检索货物"), SHIP("检索舰船"),

    // staff type filter
    STAFF_TYPE_OFFICER("雇员: 军官") {
        @Override
        public DialogOption getNext() {
            return STAFF_TYPE_ADMIN;
        }
    },
    STAFF_TYPE_ADMIN("雇员: 管理员") {
        @Override
        public DialogOption getNext() {
            return STAFF_TYPE_OFFICER;
        }
    },

    // officers personality filter
    OFFICER_TIMID("军官: 胆小") {
        @Override
        public DialogOption getNext() {
            return OFFICER_CAUTIOUS;
        }
    },
    OFFICER_CAUTIOUS("军官: 谨慎") {
        @Override
        public DialogOption getNext() {
            return OFFICER_STEADY;
        }
    },
    OFFICER_STEADY("军官: 沉着") {
        @Override
        public DialogOption getNext() {
            return OFFICER_AGGRESSIVE;
        }
    },
    OFFICER_AGGRESSIVE("军官: 激进") {
        @Override
        public DialogOption getNext() {
            return OFFICER_RECKLESS;
        }
    },
    OFFICER_RECKLESS("军官: 鲁莽") {
        @Override
        public DialogOption getNext() {
            return OFFICER_TIMID;
        }
    },

    // cargo type filter
    CARGO_TYPE_WEAPON("货物: 武器") {
        @Override
        public DialogOption getNext() {
            return CARGO_TYPE_FIGHTER;
        }
    },
    CARGO_TYPE_FIGHTER("货物: 战机联队") {
        @Override
        public DialogOption getNext() {
            return CARGO_TYPE_MODSPEC;
        }
    },
        CARGO_TYPE_MODSPEC("货物: 船体插件") {
        @Override
        public DialogOption getNext() {
            return CARGO_TYPE_BLUEPRINT;
        }
    },
    CARGO_TYPE_BLUEPRINT("货物: 蓝图") {
        @Override
        public DialogOption getNext() {
            return CARGO_TYPE_WEAPON;
        }
    },

    // market weapon size filter
    WEAPON_SIZE_ANY("安装槽位: 全部") {
        @Override
        public DialogOption getNext() {
            return WEAPON_SIZE_SMALL;
        }
    },
    WEAPON_SIZE_SMALL("安装槽位: 小型") {
        @Override
        public DialogOption getNext() {
            return WEAPON_SIZE_MEDIUM;
        }
    },
    WEAPON_SIZE_MEDIUM("安装槽位: 中型") {
        @Override
        public DialogOption getNext() {
            return WEAPON_SIZE_LARGE;
        }
    },
    WEAPON_SIZE_LARGE("安装槽位: 大型") {
        @Override
        public DialogOption getNext() {
            return WEAPON_SIZE_ANY;
        }
    },

    // market weapon type filter
    WEAPON_TYPE_ANY("安装类型: 全部") {
        @Override
        public DialogOption getNext() {
            return WEAPON_TYPE_BALLISTIC;
        }
    },
    WEAPON_TYPE_BALLISTIC("安装类型: 实弹") {
        @Override
        public DialogOption getNext() {
            return WEAPON_TYPE_ENERGY;
        }
    },
    WEAPON_TYPE_ENERGY("安装类型: 能量") {
        @Override
        public DialogOption getNext() {
            return WEAPON_TYPE_MISSILE;
        }
    },
    WEAPON_TYPE_MISSILE("安装类型: 导弹") {
        @Override
        public DialogOption getNext() {
            return WEAPON_TYPE_ANY;
        }
    },

    // market wing type filter
    WING_TYPE_ANY("设计类型: 全部") {
        @Override
        public DialogOption getNext() {
            return WING_TYPE_INTERCEPTOR;
        }
    },
    WING_TYPE_INTERCEPTOR("应用类型: 截击机") {
        @Override
        public DialogOption getNext() {
            return WING_TYPE_FIGHTER;
        }
    },
    WING_TYPE_FIGHTER("应用类型: 战斗机") {
        @Override
        public DialogOption getNext() {
            return WING_TYPE_BOMBER;
        }
    },
    WING_TYPE_BOMBER("应用类型: 轰炸机") {
        @Override
        public DialogOption getNext() {
            return WING_TYPE_ANY;
        }
    },

    // ship page
    SHIP_SIZE_FRIGATE("舰船类别: 护卫舰") {
        @Override
        public DialogOption getNext() {
            return SHIP_SIZE_DESTROYER;
        }
    },
    SHIP_SIZE_DESTROYER("舰船类别: 驱逐舰") {
        @Override
        public DialogOption getNext() {
            return SHIP_SIZE_CRUISER;
        }
    },
    SHIP_SIZE_CRUISER("舰船类别: 巡洋舰") {
        @Override
        public DialogOption getNext() {
            return SHIP_SIZE_CAPITAL;
        }
    },
    SHIP_SIZE_CAPITAL("舰船类别: 主力舰") {
        @Override
        public DialogOption getNext() {
            return SHIP_SIZE_FRIGATE;
        }
    },

    // ship max d-mod count filter
    SHIP_DAMAGED_YES("受损: 包括") {
        @Override
        public DialogOption getNext() {
            return SHIP_DAMAGED_NO;
        }
    },
    SHIP_DAMAGED_NO("受损: 排除") {
        @Override
        public DialogOption getNext() {
            return SHIP_DAMAGED_ONLY;
        }
    },
    SHIP_DAMAGED_ONLY("受损: 只限") {
        @Override
        public DialogOption getNext() {
            return SHIP_DAMAGED_YES;
        }
    },

    // ship carrier ship filter
    SHIP_CARRIER_YES("航母: 包括") {
        @Override
        public DialogOption getNext() {
            return SHIP_CARRIER_NO;
        }
    },
    SHIP_CARRIER_NO("航母: 排除") {
        @Override
        public DialogOption getNext() {
            return SHIP_CARRIER_ONLY;
        }
    },
    SHIP_CARRIER_ONLY("航母: 只限") {
        @Override
        public DialogOption getNext() {
            return SHIP_CARRIER_YES;
        }
    },

    // ship civilian ship filter
    SHIP_CIVILIAN_YES("民用: 包括") {
        @Override
        public DialogOption getNext() {
            return SHIP_CIVILIAN_NO;
        }
    },
    SHIP_CIVILIAN_NO("民用: 排除") {
        @Override
        public DialogOption getNext() {
            return SHIP_CIVILIAN_ONLY;
        }
    },
    SHIP_CIVILIAN_ONLY("民用: 只限") {
        @Override
        public DialogOption getNext() {
            return SHIP_CIVILIAN_YES;
        }
    };

    final private String name;

    private DialogOption(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public DialogOption getNext() {
        return null;
    }

    public boolean isType(String prefix) {
        return name().toString().startsWith(prefix);
    }
}
