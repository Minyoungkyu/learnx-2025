<script lang="ts">
  let agreed = false;
  let personalInfoAgreed = false;
</script>

<div class="container">
  <h1 class="title">런엑스 회원가입</h1>
  <p class="subtitle">런엑스 이용을 위해 아래 약관과 정책을 확인해 주세요.</p>

  <div class="agreement-box">
    <label class="agreement-item">
      <input type="checkbox" bind:checked={agreed}>
      <span>런엑스 이용약관에 동의합니다. <span class="required">(필수)</span></span>
    </label>
    <div class="terms-content">
      런엑스 서비스 이용약관
      <div class="terms-text">제 1조 목적</div>
    </div>
  </div>

  <div class="agreement-box">
    <label class="agreement-item">
      <input type="checkbox" bind:checked={personalInfoAgreed}>
      <span>개인정보 수집 및 이용에 동의합니다. <span class="required">(필수)</span></span>
    </label>
    <div class="terms-content">
      [개인정보 수집 및 이용 동의]
      <div class="terms-text">
        (주)TMD는 다음과 같이 개인정보를 수집 및 이용합니다.
        <br>
        1.수집하는 개인정보
      </div>
    </div>
  </div>

  <button class="next-button" disabled={!agreed || !personalInfoAgreed}>
    다음
  </button>
</div>

<style>
  .container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
  }

  .title {
    text-align: center;
    font-size: 24px;
    margin-bottom: 10px;
  }

  .subtitle {
    text-align: center;
    color: #666;
    margin-bottom: 30px;
  }

  .agreement-box {
    background-color: #f8f8ff;
    border-radius: 8px;
    padding: 15px;
    margin-bottom: 20px;
  }

  .agreement-item {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-bottom: 10px;
  }

  .required {
    color: #4A90E2;
  }

  .terms-content {
    background-color: white;
    padding: 15px;
    border-radius: 4px;
    font-size: 14px;
  }

  .terms-text {
    margin-top: 10px;
    color: #666;
  }

  .next-button {
    width: 100%;
    padding: 15px;
    background-color: #ccc;
    border: none;
    border-radius: 4px;
    color: white;
    font-size: 16px;
    cursor: pointer;
  }

  .next-button:not([disabled]) {
    background-color: #4A90E2;
  }

  .next-button:disabled {
    cursor: not-allowed;
  }
</style>
