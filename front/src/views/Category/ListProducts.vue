<template>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center">
                <h4 class="pt-3"> {{category.nome}}</h4>
                <h5 style="font-weight: 300; color: #686868"> {{msg}} </h5>
            </div>
        </div>

        <div class="row">
            <div v-for="product of category.produtos" :key="product.id"
                 class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex">
                <ProductBox :product="product" />
            </div>
        </div>
    </div>
</template>
<script>
    import ProductBox from "../../components/ProductBox";
    export default {
        components: {ProductBox},
        data() {
            return {
                id: null,
                category: {},
                msg: ''
            }
        },
        props: ["categories"],
        mounted() {
            this.id = this.$route.params.id;
            this.category = this.categories.find(category => category.id == this.id)
            if (this.category.produtos.length == 0) {
                this.msg = "Não foram encontrado produtos"
            } else if (this.category.produtos.length == 1) {
                this.msg = "Somente 1 produto encontrado"
            } else {
                this.msg = this.category.produtos.length + " produtos encontrados"
            }
        }
    }
</script>
