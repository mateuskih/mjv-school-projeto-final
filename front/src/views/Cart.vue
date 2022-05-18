<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3 mt-3">Carrinho de compras</h3>
      </div>
    </div>
    <div v-if="status == 'NOVO'">
      <div v-for="cartItem in cartItems" :key="cartItem.id" class="row mt-2 pt-3 ">
        <div class="col-2"></div>
        <div class="col-md-3">
          <img
            :src="cartItem.produto.imagemUrl"
            alt=""
            class="w-100 card-image-top embed-responsive embed-responsive-16by9"
            style="object-fit: cover"
            height="120"
          />
        </div>
        <div class="col-md-5 px-3">
          <div class="card-block px-3">
            <h6 class="card-title">
              {{ cartItem.produto.nome }}
            </h6>
            <p class="mb-0 font-weight-bold" id="item-price">
              R$ {{ cartItem.produto.preco }}
            </p>
            <p class="mb-0">Quantidade:{{ cartItem.quantidade }}</p>
          </div>
          <p class="mb-0">
            Total:
            <span class="font-weight-bold"> R$ {{ cartItem.subtotal }} </span>
          </p>
        </div>
        <div class="col-2">
          <button class="btn btn-danger btn-sm" @click="delCartItem(cartItem.id)">
            X
          </button>
        </div>
        <div class="col-12"><hr /></div>
      </div>
      <div class="total-cost pt-2 text-right">
        <h5>Total : R${{ totalCost }}</h5>
      </div>
      <div class="input-group col-md-3 col-4 p-0">
        <button
          class="btn"
          type="button"
          id="add-to-cart-button"
          @click="fecharPedido"
        >
          Fechar pedido
        </button>
      </div>
    </div>
    <div v-else class="row mt-2 pt-3">
        <div class="col-2"></div>
        <div class="col-md-3">
          <img
            :src= "'https://img.freepik.com/vetores-gratis/receber-pedidos-por-telefone-central-de-atendimento-da-loja-suporte-ao-cliente-pedido-facil-entrega-rapida-servico-comercial-personagem-de-desenho-animado-do-operador-de-call-center_335657-2564.jpg?w=2000'"
            alt=""
            class="w-100 card-image-top embed-responsive embed-responsive-16by9"
            style="object-fit: cover"
            height="120"
          />
        </div>
        <div class="col-md-5 px-3">
          <div class="card-block px-3">
            <h6 class="card-title font-weight-bold">
               PEDIDO EFETUADO COM SUCESSO
            </h6>
          </div>
          <h6 class="card-title font-weight-bold">
              Itens:
            </h6>
          <h6 class="card-title" v-for="cartItem in cartItems" :key="cartItem.id">
              {{ cartItem.produto.nome }} x {{ cartItem.quantidade }} = R$ {{ cartItem.subtotal }}
          </h6>
          <p class="mb-0">
            Total:
            <span class="font-weight-bold"> R$ {{ totalCost }} </span>
          </p>
          <p class="mb-0 font-weight-bold" id="item-price">
              STATUS: {{ status }}
            </p>
          <p>Aguarde as próximas etapadas da compra</p>
        </div>
        <div class="col-12"><hr /></div>
    </div>
  </div>
</template>
<script>
import swal from "sweetalert";
import axios from "axios";
export default {
  data() {
    return {
      cartItems: [],
      token: null,
      totalCost: 0,
      status: null
    };
  },
  props: ["baseURL"],
  methods: {
    // fetch All items in cart
    listCartItems() {
      axios
        .get(`${this.baseURL}pedido/?token=${this.token}`)
        .then((res) => {
          const result = res.data;
          this.cartItems = result.carrinhoItens;
          this.totalCost = result.total;
          this.status = result.status;
        })
        .catch((err) => console.log("err", err));
    },
    delCartItem(idItem) {
      axios
        .delete(`${this.baseURL}pedido/delete/${idItem}?token=${this.token}`)
        .then((res) => {
          if (res.status == 200) {
            swal({
              text: "Item removido com sucesso!",
              icon: "success",
            });
          }
        })
        .catch((err) => console.log("err", err));
      location.reload();
    },
    fecharPedido() {
      const fechar = "fechar"
      axios
        .post(`${this.baseURL}pedido/${fechar}?token=${this.token}`, {
        })
        .then((res) => {
          if (res.status == 200) {
            swal({
              text: "Carrinho fechado!",
              icon: "success",
            });
          }
        })
        .catch((err) => console.log("err", err));
    }
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.listCartItems();
  },
};
</script>
