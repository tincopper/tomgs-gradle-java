buildSrc

用于存放脚本插件的地方
```
buildSrc
-- src
    --main
      --groovy
      --kotlin
```
对于多项目构建，只能有一个buildSrc目录，它必须位于根项目目录中。buildSrc应该比脚本插件更受欢迎，因为它更容易维护、重构和测试代码。