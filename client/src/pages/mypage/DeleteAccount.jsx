import React from 'react';
import MyPage from './MyPage';

export default function DeleteAccount() {
  return (
    <MyPage>
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
        <button className="bg-gray w-44 my-10">탈퇴하기</button>
      </div>
    </MyPage>
  );
}
