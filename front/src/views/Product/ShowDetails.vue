<template>
  <div class="container">
    <div class="row pt-5">
      <div class="col-md-3"></div>
      <div class="col-md-6 col-12 pt-3 pt-md-0 ">
        <img :src="product.imagemUrl" class="img-fluid mb-3" />
        <h1 class="font-weight-bold">{{ product.nome }}</h1>
        <h2 class="font-weight-bold ">R$ {{ product.preco }}</h2>
        <p class="form-control">
          {{ product.descricao }}
        </p>
        <div class="d-flex flex-row justify-content-between">
          <div class="input-group col-md-3 col-4 p-0">
            <div class="input-group-prepend">
              <span class="input-group-text">Unidade</span>
            </div>
            <input type="number" class="form-control" v-model="quantity" />
          </div>

          <div class="input-group col-md-3 col-4 p-0">
            <button
              class="btn"
              type="button"
              id="add-to-cart-button"
              @click="addToCart"
            >
              Adicionar ao carrinho
            </button>
          </div>
        </div>
        <div class="features pt-3">
          <h5><strong>Detalhes da compra</strong></h5>
          <ul>
            <li>Lorem ipsum dolor sit amet consectetur adipisicing elit.</li>
            <li>Officia quas, officiis eius magni error magnam voluptatem</li>
            <li>nesciunt quod! Earum voluptatibus quaerat dolorem doloribus</li>
            <li>molestias ipsum ab, ipsa consectetur laboriosam soluta et</li>
            <li>ut doloremque dolore corrupti, architecto iusto beatae.</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import swal from "sweetalert";
import axios from "axios";
export default {
  props: ["baseURL", "products", "categories"],
  data() {
    return {
      product: {},
      category: {},
      id: null,
      token: null,
      quantity: 1,
      wishListString: "Add to wishlist",
    };
  },
  methods: {
    addToCart() {
      if (!this.token) {
        swal({
          text: "Logue para adicionar ao carrinho",
          icon: "error",
        });
        return;
      }
      axios
        .post(`${this.baseURL}pedido/?token=${this.token}`, {
          produtoId: this.id,
          quantidade: this.quantity,
        })
        .then((res) => {
          if (res.status == 200) {
            swal({
              text: "Product adicionado ao carrinho",
              icon: "success",
            });
          }
        })
        .catch((err) => console.log("err", err));
    },
  },
  mounted() {
    this.id = this.$route.params.id;
    this.product = this.products.find(product => product.id == this.id)
    this.token = localStorage.getItem("token");
  },
};
</script>
<style>
.category {
  font-weight: 400;
}
#wishlist-button {
  background-color: #b9b9b9;
}
#add-to-cart-button {
  background-color: #febd69;
}
</style>