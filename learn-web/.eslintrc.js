module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: ['plugin:vue/essential', '@vue/standard'],
  parserOptions: {
    parser: 'babel-eslint'
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'spaced-comment': 0,
    'eol-last': 0,
    'quote-props': 0,
    'space-before-function-paren': 0,
    'semi': [2, "always"], // 语句强制分号结尾
    'indent': [2, 4], // 强制使用一致的缩进
    'arrow-spacing': 0, // 强制箭头函数的箭头前后使用一致的空格
    'no-unused-vars': [0, { 'vars': 'local' }] // 不能有声明后未被使用的变量或参数
  }
}