<template>
    <div class="container mt-5">
        <div class="row">
            <div class="col-12 text-center">
                <h4>Adicionar Produto</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form>
                    <div class="form-group">
                        <label>Categoria</label>
                        <select class="form-control" v-model="categoryId" required>
                            <option v-for="category in categories" :key="category.id"
                                    :value="category.id">{{ category.nome }}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" v-model="name" class="form-control" >
                    </div>
                    <div class="form-group">
                        <label>Descrição</label>
                        <input type="text" v-model="description" class="form-control" >
                    </div>
                    <div class="form-group">
                        <label>Url da imagem</label>
                        <input type="text"  v-model="imageURL" class="form-control" >
                    </div>
                    <div class="form-group">
                        <label>Preço</label>
                        <input type="number" v-model="price" class="form-control" >
                    </div>
                    <button type="button" class="btn btn-primary" @click="addProduct">Adicionar Produto</button>
                </form>
            </div>
            <div class="col-3"></div>
        </div>

<!--        Form-->
    </div>
</template>
<script>
    import axios from 'axios'
    import swal from 'sweetalert'
    export default {
        props: ["baseURL", "categories"],
        data() {
            return {
                id: null,
                categoryId: null,
                name: null,
                description: null,
                imageURL: null,
                price: null
            }
        },
        methods: {
            addProduct() {
                const newProduct = {
                    categoriaId: this.categoryId,
                    descricao: this.description,
                    nome: this.name,
                    imagemUrl: this.imageURL,
                    preco: this.price
                };

                axios.post(this.baseURL+"produtos", newProduct)
                .then(() => {
                    this.$router.push({name: 'AdminProduct'});
                    swal({
                        text: "Produto adicionado!",
                        icon: "success"
                    })
                }).catch((err)=> {
                    console.log("err", err);
                })


            }
        }
    }
</script>
