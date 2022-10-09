import React from 'react';
import { useRecoilValue } from 'recoil';
import instance from '../../service/request';
import { memberIdState } from '../../recoil/atoms/memberIdState';
import { authorizationState } from '../../recoil/atoms/authorizationState';

export default function DeleteAccount() {
  const memberId = useRecoilValue(memberIdState);
  const token = useRecoilValue(authorizationState);

  const deleteAccountHandler = async () => {
    try {
      await instance.delete(`/members/${memberId}`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token,
        },
      });
      alert('회원탈퇴가 되었습니다 ! 그동안 서비스를 이용해주셔서 감사합니다.');
      window.localStorage.clear();
      window.location.replace('/');
    } catch (err) {
      console.log('err', err);
    }
  };
  return (
    <>
      <p className="text-center mb-10 text-3xl sm:text-2xl sm:my-4">회원탈퇴</p>
      <section className="flex flex-col items-center bg-white m-10 rounded-lg">
        <div className="flex flex-col items-center p-10 text-2xl sm:text-xl">
          <p>회원 탈퇴시</p>
          <p>FLY AWAY 서비스를 이용하지 못합니다.</p>
          <p>그래도 탈퇴하시겠습니까?</p>
        </div>
        <div className="flex flex-col items-center px-10 pb-10 text-2xl sm:text-xl">
          <p>탈퇴를 원하실 경우</p>
          <p>아래 탈퇴하기 버튼을 눌러주세요.</p>
        </div>
        <button
          className="bg-pink p-1 md:w-60 sm:w-48 mb-10 text-2xl sm:text-xl rounded-lg"
          onClick={deleteAccountHandler}
        >
          탈퇴하기
        </button>
      </section>
    </>
  );
}
