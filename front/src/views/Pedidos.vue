<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3 mt-3">Carrinho de compras</h3>
      </div>
    </div>
    <div>
      <div v-for="pedido in pedidos" :key="pedido.id" class="row mt-2 pt-3 ">
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
            <h6 class="card-title ">
              Cliente: {{ pedido.cliente.nome }}
            </h6>
            <p class="mb-0 font-weight-bold" id="item-price">
              Status: {{ pedido.status }}
            </p>
          </div>
          <p class="mb-0">
            Endereço:
            <span class="font-weight-bold"> {{ pedido.cliente.endereco.logradouro }} </span><br>
             <span class="font-weight-bold"> {{ pedido.cliente.endereco.numero }} </span><br>
              <span class="font-weight-bold"> {{ pedido.cliente.endereco.bairro }} </span>
          </p>
          <button
          class="btn btn-success mt-3"
          type="button"
          @click="fecharPedido('entregue', pedido.cliente.token)"
        >
          ENTREGUE
        </button>
        <button
          class="btn btn-danger ml-3 mt-3"
          type="button"
          @click="fecharPedido('cancelar', pedido.cliente.token)"
        >
          CANCELAR
        </button>
        </div>
        <h5>Total: R$ {{ pedido.total }}</h5>
        <div class="col-12"><hr /></div>
      </div>
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
      pedidos: null,
    };
  },
  props: ["baseURL"],
  methods: {
    // fetch All items in cart
    listarPedidos() {
      axios
        .get(`${this.baseURL}pedido/all?token=${this.token}`)
        .then((res) => {
          const result = res.data;
          this.pedidos = result;
        })
        .catch((err) => console.log("err", err));
    },
    fecharPedido(status, token) {
      axios
        .post(`${this.baseURL}pedido/${status}?token=${token}`, {
        })
        .then((res) => {
          if (res.status == 200) {
            swal({
              text: "Pedido alterado!",
              icon: "success",
            });
          }
        })
        .catch((err) => console.log("err", err));
    }
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.listarPedidos();
  },
};
</script>
