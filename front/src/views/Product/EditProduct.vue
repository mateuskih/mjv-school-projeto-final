<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3"> Editar Produto</h4>
            </div>
        </div>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <form v-if="product">
                    <div class="form-group">
                        <label>Categoria</label>
                        <select class="form-control" v-model="product.categoriaId" required>
                            <option v-for="category of categories"
                                    :key="category.id"
                                    :value="category.id"> {{category.nome}}</option>

                        </select>
                    </div>
                    <div class="form-group">
                        <label>Nome</label>
                        <input type="text" class="form-control" v-model="product.nome" required/>
                    </div>
                    <div class="form-group">
                        <label>Descrição</label>
                        <input type="text" class="form-control" v-model="product.descricao" required/>
                    </div>
                    <div class="form-group">
                        <label>Url da imagem</label>
                        <input type="text" class="form-control" v-model="product.imagemUrl" required/>
                    </div>
                    <div class="form-group">
                        <label>Preço</label>
                        <input type="number" class="form-control" v-model="product.preco" required/>
                    </div>
                    <button type="button" class="btn btn-primary"
                            @click="editProduct" >Enviar</button>
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
                product: null,
                id: null
            }
        },
        props: ["baseURL", "categories", "products"],
        methods: {
            async editProduct() {
                await axios.put(`${this.baseURL}produtos/${this.id}`,
                    this.product)
                    .then(() => {
                        this.$emit("fetchData");
                        this.$router.push({name: 'AdminProduct'})
                        swal({
                            text: "Produto editado com sucesso!",
                            icon: "success"
                        })
                    }).catch(err => console.log('err', err));
            }
        },
        mounted() {
            this.id = this.$route.params.id;
            this.product = this.products.find(product => product.id == this.id)
        }
    }
</script>
