<script lang="ts">
  import rq from '$lib/rq/rq.svelte';

  async function submitLoginForm() {
    const form = document.querySelector('form') as HTMLFormElement;
    const formData = new FormData(form);

    const { data, error } = await rq.apiEndPoints().POST('/api/v1/members/login', {
      body: {
        username: formData.get('username') as string,
        password: formData.get('password') as string
      }
    });

    if (data) {
      rq.setLogined(data.data.item);
    }

    rq.msgAndRedirect(data, error, '/');
  }
</script>

<form onsubmit={submitLoginForm}>
  <div class="flex-grow flex items-center justify-center">
    <input type="text" name="username" placeholder="아이디" />
    <input type="password" name="password" placeholder="비밀번호" />
    <button type="submit">로그인</button>
  </div>
</form>
<div class="flex-grow flex items-center justify-center">
  <a href={rq.getKakaoLoginUrl()} class="btn">
    <i class="fa-solid fa-comment text-[color:#ffe812]"></i>
    카카오로 3초만에 시작하기
  </a>
</div>
