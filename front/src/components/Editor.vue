<template lang="html">
  <div>
    <el-row :gutter="20" class="center">
      <div style="padding:20px">
        <el-avatar :size="150" src="../../static/image/headLogo.jpg"></el-avatar>
      </div>
    </el-row>
  
    <div class="editor">
      <div ref="toolbar" class="toolbar"></div>
      <div ref="editor" class="text"></div>
    </div>
    <el-row :gutter="20" style="padding-top:20px" class="center">
      <el-button type="primary" size="default" @click="submitContent">
        保存
      </el-button>
  
      <el-button type="success" size="default" @click="submitContent">
        提交
      </el-button>
    </el-row>
  </div>
</template>

<script>
  import E from "wangeditor";
  export default {
    data() {
      return {
        // uploadPath,
        editor: null,
        value: "东东爱小雅"
      };
    },
  
    mounted() {
      this.seteditor();
      this.editor.txt.html(this.value);
    },
    methods: {
      click() {
        alert(this.value);
      },
      seteditor() {
        this.editor = new E(this.$refs.toolbar, this.$refs.editor);
  
        this.editor.config.onchange = html => {
          this.value = html; // 绑定当前逐渐地值
        };
        // 创建富文本编辑器
        this.editor.create();
      }
    }
  };
</script>

<style lang="css">
  .editor {
    width: 60%;
    margin: 0 auto;
    position: relative;
    z-index: 0;
  }
  
  .toolbar {
    border: 1px solid #ccc;
  }
  
  .text {
    border: 1px solid #ccc;
    min-height: 300px;
  }
  
  .center {
    text-align: center;
  }
</style>