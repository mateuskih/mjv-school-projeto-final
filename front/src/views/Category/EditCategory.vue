<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3"> Editar Categoria</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form v-if="category">
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" class="form-control"
                               v-model="category.nome"  required/>
                    </div>
                    <div class="form-group">
                        <label>Descrição</label>
                        <input type="text" class="form-control"
                               v-model="category.descricao"  required/>
                    </div>
                    <div class="form-group">
                        <label>Url da Imagem</label>
                        <input type="text" class="form-control"
                               v-model="category.imagemUrl"  required/>
                    </div>
                    <button type="button" class="btn btn-primary" @click="editCategory" >Submit</button>
                </form>
            </div>
            <div class="col-3"></div>
        </div>
    </div>
</template>
<script>
    import axios from 'axios'
    import swal from 'sweetalert'
    export default {
        data() {
            return {
                category: null,
                id: null
            }
        },
        props: ["baseURL", "categories"],
        methods: {
            async editCategory() {
                delete this.category["produtos"]
                console.log('category', this.category)
                await axios.post(`http://localhost:8080/categorias`,
                    this.category)
                .then(() => {
                    this.$emit("fetchData");
                    this.$router.push({name: 'Category'})
                    swal({
                        text: "Categoria editada com sucesso!",
                        icon: "success"
                    })
                }).catch(err => console.log('err', err));
            }
        },
        mounted() {
            this.id = this.$route.params.id;
            this.category = this.categories.find(category => category.id == this.id)
        }
    }
</script>
