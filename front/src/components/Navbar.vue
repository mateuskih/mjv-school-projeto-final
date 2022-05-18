<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <!-- Navbar content -->
    <!--    Logo-->
    <router-link class="navbar-brand mt-2" :to="{ name: 'Home' }">
      <p>MJV SCHOOL PROJETO FINAL</p>
    </router-link>
    <!--    Burger Button-->
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <!--      Search Bar-->
      <form class="form-inline ml-auto mr-auto">
        <div class="input-group">
          <input
            size="100"
            type="text"
            class="form-control"
            placeholder="Search Items"
            aria-label="Username"
            aria-describedby="basic-addon1"
          />
          <div class="input-group-prepend">
            <span class="input-group-text" id="search-button-navbar">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="16"
                height="16"
                fill="currentColor"
                class="bi bi-search"
                viewBox="0 0 16 16"
              >
                <path
                  d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
                />
              </svg>
            </span>
          </div>
        </div>
      </form>
      <router-link :to="{name: 'Category'}" class="mt-3" v-if="role === 'ADMIN'"> 
          <button class="btn btn-primary btn-sm "> Admin Categorias</button>
      </router-link>
      <router-link :to="{name: 'AdminProduct'}" class="ml-3 mt-3" v-if="role === 'ADMIN'">
          <button class="btn btn-primary btn-sm"> Admin Produtos</button>
      </router-link>
      <router-link :to="{name: 'AdminPedidos'}" class="ml-3 mt-3" v-if="role === 'ADMIN'">
          <button class="btn btn-primary btn-sm"> Admin Pedidos</button>
      </router-link>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarAccount"
            data-toggle="dropdown"
          >
            Navegar
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarAccount">
            <router-link class="dropdown-item" :to="{ name: 'Home' }"
              >Inicio
            </router-link>
            <router-link class="dropdown-item" :to="{ name: 'Home' }"
              >Produtos
            </router-link>
            <router-link class="dropdown-item" :to="{ name: 'Home' }"
              >Categorias
            </router-link>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarAccount"
            data-toggle="dropdown"
          >
            Conta
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarAccount">
            <router-link
              v-if="!token"
              class="dropdown-item"
              :to="{ name: 'Signup' }"
              >Criar
            </router-link>
            <router-link
              v-if="!token"
              class="dropdown-item"
              :to="{ name: 'Signin' }"
              >Entrar
            </router-link>
            <a class="dropdown-item" v-if="token" href="#" @click="signout"
              >Sair
            </a>
          </div>
        </li>
        <li class="nav-item">
          <router-link class="text-light" :to="{ name: 'Cart' }">
            <i class="fa fa-shopping-cart" style="font-size:36px"></i>
          </router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>
<script>
export default {
  name: "Navbar",
  data() {
    return {
      token: null,
      role: null,
    };
  },
  methods: {
    signout() {
      localStorage.removeItem("token");
      localStorage.removeItem("role");
      this.token = null;

    },
  },
  mounted() {
    this.token = localStorage.getItem("token");
    this.role = localStorage.getItem("role");
  },
};
</script>
<style scoped>
#logo {
  width: 150px;
  margin-left: 20px;
  margin-right: 20px;
}
.nav-link {
  color: rgba(255, 255, 255);
}
</style>
