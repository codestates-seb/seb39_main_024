import React from 'react';
import { useRecoilValue } from 'recoil';
import instance from '../../service/request';
import { memberIdState } from '../../recoil/atoms/memberIdState';

export default function DeleteAccount() {
  const memberId = useRecoilValue(memberIdState);

  const deleteAccountHandler = async () => {
    try {
      await instance.delete(`/members/${memberId}`);
      alert('회원탈퇴가 되었습니다 ! 그동안 서비스를 이용해주셔서 감사합니다.');
      window.location.replace('/');
    } catch (err) {
      console.log('err', err);
    }
  };
  return (
    <>
      <p className="text-center mb-10 text-xl sm:my-4">회원탈퇴</p>
      <div className="flex flex-col items-center bg-white m-3">
        <div className="flex flex-col items-center my-10">
          <p>회원 탈퇴시 FLY AWAY 서비스를 이용하지 못합니다.</p>
          <p>그래도 탈퇴하시겠습니까?</p>
        </div>
        <div className="flex flex-col items-center">
          <p>탈퇴를 원하실 경우</p>
          <p>아래 탈퇴하기 버튼을 눌러주세요.</p>
        </div>
        <button className="bg-gray w-44 my-10" onClick={deleteAccountHandler}>
          탈퇴하기
        </button>
      </div>
    </>
  );
}
