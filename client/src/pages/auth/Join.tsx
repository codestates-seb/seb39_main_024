import React from 'react';

export default function Join() {
  return (
    <main>
      <p className="text-center sm:text-xl md:text-3xl">
        FLY AWAY에 회원가입 하기
      </p>
      <form className="flex flex-col items-center bg-gray">
        <label htmlFor="name">이름</label>
        <input id="name" type="text" />
        <label htmlFor="email">이메일</label>
        <input id="email" type="email" />
        <label htmlFor="password">비밀번호</label>
        <input id="password" type="password" />
        <label htmlFor="check">비밀번호 확인</label>
        <input id="check" type="password" />
        <button className="rounded bg-pink px-1.5">회원가입</button>
      </form>
    </main>
  );
}
