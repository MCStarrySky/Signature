# Signature

该插件原本为自己的服务器写的. 本插件支持你设置你的个人签名, 并通过 Papi 变量 (%signature_signature%) 的形式展示在任何地方. 你可以查看自己或他人的历史签名, 包括时间内容等, 还可以点赞, 也可以隐藏自己的历史签名.


## 特性
- 可以配置每次设置个签文本所限制的长度
- 可以配置每次更改签名花费的游戏币数量
- 玩家数据文件可以配置几乎所有东西
- 玩家数据文件结构简单、鲜明、易读

## 配置文件

#### In settings.yml:

```yaml
Language: Chinese
bStats: true
Debug: false
EditCost: 50.0
DefaultSignature: "这个人很懒, 还没有写签名."
LengthLimit: 20
```

#### In Gui.yml:

```yaml
Settings:
  # Gui 标题.
  Title: "{0} 的历史个性签名"
  # Gui 行数.
  Row: 6
Items:
  # 显示模板.
  Template:
    ItemStack:
      Display: "&r个签"
      Material: "BED"
      Lore:
        - ''
        - '  &7左键对该个签「&3点赞&7」.'
        - '  &7时间: &a{0}&7.'
        - '  &7内容: &a{1}&7.'
        - ''
    LikeFirst: '  &7点赞列表:'
    LikeEnd: ''
    Like: '  &7- &a{0}&7 于 &b{1}&7.'
    Position:
      X: "1-9"
      Y: "1-5"
  Next:
    ItemStack:
      Display: "&r下一页"
      Material: BOOK
      Lore:
        - ''
        - '  &7点击翻到 &c下一页.'
        - ''
    Position:
      X: "8"
      Y: "6"
  Previous:
    ItemStack:
      Display: "&r上一页"
      Material: BOOK
      Lore:
        - ''
        - '  &7点击翻到 &c上一页.'
        - ''
    Position:
      X: "2"
      Y: "6"
```

## 截图

![98aa10e39fe1f06f499ceb4bca2c5d9.jpg](https://i.loli.net/2021/03/31/hqbAPDK1GHQydfi.jpg)
![a3e51cb63fc492b0d640e4a14b75750.png](https://i.loli.net/2021/03/31/sYHAeu39Ttn6xbL.png)
![dcc66aa06496b70b441d83ab27dfe7c.jpg](https://i.loli.net/2021/03/31/S2L9RtvQUfbgHda.jpg)


## 附加信息
- 本插件通过爱发电平台付费, 地址: https://afdian.net/@mical
- 如果你不愿意购买, 可以自行前往 Github 自行编译获得最新版 JAR 文件
- 但是你将不会得到任何技术支持和帮助.
- 购买之后可以获得技术支持, 安装帮助, 问题帮助
- 你也可以克隆源代码修改内容, 但禁止传播二次修改的任何内容
- 本插件所有所用代码均为原创, 不存在借用/抄袭等行为.