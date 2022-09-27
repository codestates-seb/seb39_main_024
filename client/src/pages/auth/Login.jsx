import { useState } from 'react';
import axios from 'axios';

export default function Login() {
  const [inputValue, setInputValue] = useState({
    email: '',
    password: '',
  });

  const inputValueChangeHandler = (e) => {
    setInputValue({
      ...inputValue,
      [e.target.name]: e.target.value,
    });
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    const item = {
      email: inputValue.email,
      password: inputValue.password,
    };

    await axios
      .post('http://211.41.205.19:8080/login', item)
      .then(() => {
        setInputValue({
          email: '',
          password: '',
        });
        alert('로그인 되었습니다 ! 환영합니다 :)');
      })
      .catch((e) => {
        console.log('err', e);
      });
  };
  return (
    <main>
      <p className="text-center sm:text-xl md:text-3xl">
        FLY AWAY에 로그인 하기
      </p>
      <form
        className="flex flex-col items-center bg-gray"
        onSubmit={submitHandler}
      >
        <label htmlFor="email">이메일</label>
        <input
          id="email"
          type="email"
          name="email"
          value={inputValue.email}
          onChange={inputValueChangeHandler}
        />
        <label htmlFor="password">비밀번호</label>
        <input
          id="password"
          type="password"
          name="password"
          value={inputValue.password}
          onChange={inputValueChangeHandler}
        />
        <button className="rounded bg-pink px-1.5">로그인</button>
      </form>
    </main>
  );
}
