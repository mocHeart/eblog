<template>
    <div>
        <el-container>
            <el-header>
                <img class="mlogo" src="https://www.markerhub.com/dist/images/logo/markerhub-logo.png" alt="">
            </el-header>
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="ruleForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="ruleForm.password"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                ruleForm: {
                    username: 'moc',
                    password: '123'
                },
                rules: {
                    username: [
                        // trigger 触发校验的时机：blur 失去焦点  change 数据改变
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请选择密码', trigger: 'change' }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                /* 这里的 $refs 为什么可以写成这种数组的方式？ */
                /* 和普通节点上绑定ref不同，这里是被Element封装后挂到this.refs上的 */
                // console.log(this.$refs[formName])

                /* validate 对整个表单的内容进行校验进行验证，valid: true (成功)，false(失败) */
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // console.log(this.ruleForm)
                        this.axios.post('http://127.0.0.1:8088/eblog/login', this.ruleForm)
                            .then(res => {
                                console.log(res)
                                const jwt = res.headers['authorization']
                                const userInfo = res.data.data

                                // 登录信息存储
                                this.$store.commit("SET_TOKEN", jwt)
                                this.$store.commit("SET_USERINFO", userInfo)

                                this.$router.push("/blogs")
                            })
                            .catch(error => {
                                console.log('axios: ', error)
                            })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        /*background-color: #E9EEF3;*/
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .mlogo {
        height: 60%;
        margin-top: 10px;
    }

    .demo-ruleForm {
        max-width: 500px;
        margin: 0 auto;
    }

</style>