import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import instance from '../../service/request';

export default function Join() {
  const navigate = useNavigate();

  const [inputValue, setInputValue] = useState({
    name: '',
    email: '',
    password: '',
    check: '',
  });

  const inputValueChangeHandler = (e) => {
    setInputValue({
      ...inputValue,
      [e.target.name]: e.target.value,
    });
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    if (inputValue.password !== inputValue.check) {
      alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
      return;
    }

    const item = {
      name: inputValue.name,
      email: inputValue.email,
      password: inputValue.password,
    };

    await instance
      .post('/members/join', item)
      .then(() => {
        setInputValue({
          name: '',
          email: '',
          password: '',
          check: '',
        });
        alert('회원가입 되었습니다 ! 환영합니다 :)');
        navigate('/login');
      })
      .catch((e) => {
        console.log('err', e);
        if (e.response.status === 409) {
          alert('이미 존재하는 이메일 입니다. 다른 이메일을 사용해주세요.');
        }
      });
  };
  return (
    <div className="flex flex-col items-center justify-center my-8">
      <p className="mb-8 s:text-2xl text-3xl font-bold">
        FLY AWAY에 회원가입 하기
      </p>
      <div className="flex flex-col items-center justify-center w-1/2 sm:w-4/5 bg-pale_pink rounded-lg">
        <form className="flex flex-col w-4/5 my-3" onSubmit={submitHandler}>
          <label className="h-10 leading-10" htmlFor="name">
            이름
          </label>
          <input
            className="mb-3 h-10 rounded-md"
            id="name"
            type="text"
            name="name"
            value={inputValue.name}
            onChange={inputValueChangeHandler}
          />

          <label className="h-10 leading-10" htmlFor="email">
            이메일
          </label>
          <input
            className="mb-3 h-10 rounded-md"
            id="email"
            type="email"
            name="email"
            value={inputValue.email}
            onChange={inputValueChangeHandler}
          />
          <label className="h-10 leading-10" htmlFor="password">
            비밀번호
          </label>
          <input
            className="mb-3 h-10 rounded-md"
            id="password"
            type="password"
            name="password"
            value={inputValue.password}
            onChange={inputValueChangeHandler}
          />
          <label className="h-10 leading-10" htmlFor="check">
            비밀번호 확인
          </label>
          <input
            className="mb-3 h-10 rounded-md"
            id="check"
            type="password"
            name="check"
            value={inputValue.check}
            onChange={inputValueChangeHandler}
          />
          <button className="my-6 h-10 bg-green rounded-md font-bold">
            회원가입
          </button>
        </form>
      </div>
    </div>
  );
}
