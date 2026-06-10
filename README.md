# Java Plants vs. Zombies Demo

一个基于 **Java Swing** 实现的植物大战僵尸风格课程设计项目。项目目前已经具备主菜单、关卡选择、出战植物选择、草坪战斗、阳光系统、植物种植、僵尸生成、子弹碰撞、命中特效、暂停菜单、存档读取、胜利/失败界面等基础功能。

本项目主要用于课程设计与 Java 游戏开发练习，重点不是完全复刻原版游戏，而是通过一个较完整的 Demo 学习游戏对象管理、场景切换、动画系统、碰撞检测、UI 绘制和关卡配置等内容。

---

## 项目预览

> 这里可以放项目运行截图。
> 如果截图已经上传到仓库，可以把下面路径替换成你自己的截图路径。

```markdown
![主菜单](docs/screenshots/main_menu.png)
![关卡场景](docs/screenshots/level_scene.png)
![选择植物](docs/screenshots/plant_selection.png)
```

---

## 已实现功能

### 1. 场景系统

项目已经实现多个场景之间的切换：

* 主菜单场景 `MainMenuScene`
* 关卡选择场景 `LevelSelectScene`
* 关卡战斗场景 `LevelScene`
* 暂停菜单 `PauseMenuUI`
* 胜利场景 `WinScene`
* 失败场景 `GameOverScene`

主菜单支持：

* 开始冒险
* 读取存档
* 退出游戏

关卡内支持：

* ESC 暂停
* 继续游戏
* 保存游戏
* 返回主菜单
* 退出游戏

---

### 2. 关卡选择与关卡配置

项目加入了关卡配置系统：

* `LevelDefinition`
* `SpawnEvent`
* `LevelDefinitions`
* `LevelManager`

每个关卡可以配置：

* 关卡编号
* 关卡名称
* 初始阳光
* 关卡总时间
* 僵尸生成时间
* 僵尸类型
* 僵尸生成行数

这样后续可以很方便地扩展多个关卡，而不需要把刷怪逻辑写死在 `LevelScene` 里面。

---

### 3. 出战植物选择界面

进入关卡后，玩家会先进入出战植物选择流程：

1. 镜头展示草坪
2. 镜头移动到右侧区域
3. 出现植物卡牌池
4. 玩家选择本局出战植物
5. 点击开始游戏
6. 镜头回到草坪
7. 显示 Ready / Set / Plant
8. 正式开始战斗

相关类：

* `PlantSelectionUI`
* `PlantCard`
* `CardBarUI`
* `SeedBankUI`
* `ReadySetPlantUI`

---

### 4. 植物系统

当前项目已支持基础植物逻辑：

* 豌豆射手 `Peashooter`
* 向日葵 `Sunflower`

已有机制：

* 植物卡片选择
* 阳光消耗
* 冷却时间
* 植物种植
* 向日葵生产阳光
* 豌豆射手只在本行前方存在僵尸时才发射豌豆

后续可以继续扩展：

* 坚果墙
* 寒冰射手
* 樱桃炸弹
* 双发射手
* 土豆雷

---

### 5. 僵尸系统

当前项目支持僵尸生成、移动、攻击和死亡逻辑。

已有僵尸类型包括：

* 普通僵尸 `NormalZombie`
* 路障僵尸 `ConeheadZombie`
* 铁桶僵尸 `BucketheadZombie`

僵尸生成不再是简单数组，而是通过关卡配置中的 `SpawnEvent` 控制。不同关卡可以配置不同的僵尸类型和生成节奏。

---

### 6. 子弹与碰撞系统

项目已经实现：

* 豌豆子弹飞行
* 子弹与僵尸碰撞检测
* 精细碰撞框 `getCollisionBounds()`
* 子弹命中后进入 hit 状态
* 子弹命中特效播放
* 避免子弹提前消失或重复扣血

现在豌豆命中僵尸时，不再是简单地矩形相交后立即消失，而是会播放命中特效，使战斗表现更自然。

---

### 7. 阳光系统

已实现：

* 初始阳光
* 天空阳光掉落
* 向日葵生产阳光
* 鼠标点击收集阳光
* 阳光槽显示
* 植物种植时扣除阳光
* 阳光不足时无法种植

相关类：

* `Sun`
* `SunResource`
* `SunBankUI`
* `SkySunSpawner`

---

### 8. UI 系统

项目中已经拆分出多个 UI 组件：

* `UIButton`
* `PlantCard`
* `CardBarUI`
* `CardSlot`
* `CooldownBar`
* `SeedBankUI`
* `SunBankUI`
* `ShovelUI`
* `LevelProgressUI`
* `StatusMessageUI`
* `PlantSelectionUI`
* `ReadySetPlantUI`

UI 与游戏世界坐标分离：

* 植物、僵尸、子弹、阳光、小推车使用世界坐标
* 卡牌栏、阳光槽、铲子、进度条、暂停菜单使用屏幕坐标

这样可以支持镜头平移，同时保证 UI 不被镜头影响。

---

### 9. 小推车系统

项目支持每行草坪的小推车机制：

* 每行左侧有一辆小推车
* 僵尸突破到左侧后触发小推车
* 小推车向右冲刺
* 清除本行僵尸
* 小推车冲出屏幕后消失

相关类：

* `LawnMower`

---

### 10. 存档系统

项目支持基础存档与读档：

* 游戏中可以保存当前进度
* 主菜单可以读取存档
* 存档目录位于 `resources/saves`

目前存档主要用于 Demo 展示，后续可以继续扩展为多存档、账号绑定或数据库存档。

---

### 11. 音频系统

项目包含基础音频管理：

* 背景音乐 BGM
* 按钮点击音效
* 种植音效
* 收集阳光音效
* 豌豆发射音效
* 豌豆命中音效
* 僵尸死亡音效

相关类：

* `AudioManager`

建议音频统一使用 `.wav` 格式，避免 Java 默认音频播放对 `.mp3` 支持不稳定的问题。

---

## 项目结构

```text
java-Plants-vs.-Zombies
├── src
│   └── com
│       └── xhl
│           └── pvz
│               ├── app             # 程序入口、窗口、面板
│               ├── core            # 全局配置、游戏上下文
│               ├── scene           # 场景系统
│               ├── entity          # 游戏实体
│               │   ├── plant       # 植物
│               │   ├── zombie      # 僵尸
│               │   ├── bullet      # 子弹
│               │   └── item        # 阳光、小推车等物品
│               ├── manager         # 资源、音频、场景、关卡、碰撞管理
│               ├── ui              # UI 组件
│               ├── level           # 关卡配置与刷怪事件
│               ├── factory         # 植物、僵尸、卡片工厂
│               ├── animation       # 动画系统
│               ├── lawn            # 草坪网格
│               ├── resource        # 图片资源 key
│               └── save            # 存档相关
│
├── resources
│   ├── images
│   │   ├── background
│   │   ├── ui
│   │   ├── cards
│   │   ├── plants
│   │   ├── zombies
│   │   ├── bullets
│   │   └── items
│   ├── sounds
│   │   ├── bgm
│   │   └── effect
│   └── saves
│
└── README.md
```

---

## 运行环境

建议环境：

* JDK 17 或以上
* Windows / macOS / Linux 均可
* 推荐使用 IntelliJ IDEA 或 VS Code 打开项目

项目使用 Java Swing，不依赖大型游戏引擎。

---

## 运行方式

### 方式一：使用 IDE 运行

1. 使用 IntelliJ IDEA 或 VS Code 打开项目
2. 确认 JDK 配置正确
3. 找到程序入口：

```text
src/com/xhl/pvz/app/GameApp.java
```

4. 运行 `GameApp`

---

### 方式二：命令行运行

在项目根目录执行：

#### Windows PowerShell

```powershell
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -encoding UTF-8 -d bin @sources.txt
java -cp bin com.xhl.pvz.app.GameApp
```

#### macOS / Linux

```bash
find src -name "*.java" > sources.txt
javac -encoding UTF-8 -d bin @sources.txt
java -cp bin com.xhl.pvz.app.GameApp
```

注意：运行时请保持在项目根目录下，因为程序会从 `resources` 目录读取图片和音频资源。

---

## 操作说明

### 主菜单

* 点击“开始冒险”：进入关卡选择界面
* 点击“读取存档”：读取已有存档
* 点击“退出游戏”：关闭程序

### 关卡选择

* 点击 Day 1 / Day 2 / Day 3：进入对应关卡
* 点击“返回主菜单”：回到主菜单

### 关卡内

* 鼠标点击植物卡片：选择植物
* 鼠标点击草坪格子：种植植物
* 鼠标点击阳光：收集阳光
* 鼠标点击铲子：进入铲除模式
* ESC：打开或关闭暂停菜单

---

## 资源命名规范

资源统一放在 `resources` 目录下。

要求：

1. 文件名不要使用中文
2. 文件名不要包含空格
3. 图片优先使用 `.png`
4. 动画帧从 `0.png` 开始连续命名
5. 音频建议使用 `.wav`
6. 植物、僵尸、子弹、阳光资源建议使用透明背景 PNG

示例：

```text
resources/images/plants/peashooter/idle/0.png
resources/images/plants/peashooter/idle/1.png
resources/images/zombies/normal_zombie/walk/0.png
resources/images/bullets/pea_hit/0.png
resources/sounds/effect/pea_hit.wav
```

---

## 后续计划

后续可以继续扩展：

* 更多植物

  * 坚果墙
  * 寒冰射手
  * 樱桃炸弹
  * 双发射手
  * 土豆雷

* 更多僵尸

  * 路障僵尸动画资源
  * 铁桶僵尸动画资源
  * 更复杂的僵尸状态

* 更完整的关卡系统

  * 更多关卡
  * 每关不同可选植物
  * 每关不同初始阳光
  * 大波僵尸提示

* 更完整的表现效果

  * 植物攻击动画
  * 僵尸受损阶段动画
  * 僵尸死亡动画
  * 爆炸特效
  * 冰冻特效

* 更完整的数据系统

  * 多存档
  * 排行榜
  * 登录界面
  * PostgreSQL 数据库存储

---

## 项目特点

这个项目的重点不只是做一个能玩的 Demo，而是通过拆分系统来学习游戏开发中的基本结构：

* 场景切换
* 游戏循环
* 实体管理
* UI 绘制
* 世界坐标与屏幕坐标
* 资源管理
* 动画播放
* 碰撞检测
* 关卡配置
* 存档系统

项目采用较清晰的模块拆分，方便后续继续扩展植物、僵尸、关卡和 UI。

---

## 说明

本项目为课程设计与学习用途。
项目中的素材资源请使用自制、开源、授权或课程展示允许使用的资源。
如使用来源于网络的图片或音频，请注意素材授权，不建议用于商业用途。
