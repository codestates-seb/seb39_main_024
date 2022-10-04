import Button from '../components/Button';

export default function Sidebar() {
  return (
    <aside className="flex flex-col justify-center items-center w-[300px]">
      <div className="flex flex-col items-center ">
        <img
          className="border-solid border border-zinc-300 p-2 w-48 h-48 sm:w-32 sm:h-32"
          src={'http://211.41.205.19:8080/members/1/image'}
          alt="img"
        />
        <p className="text-center mb-2.5">username</p>
      </div>
      <div className="sm:hidden flex flex-col items-center py-12">
        <Button link="/mypage" str="요약" />
        <Button link="/mypage/calendar" str="캘린더" />
        <Button link="/mypage/profile-edit" str="프로필 편집" />
        <Button link="/mypage/delete-account" str="회원 탈퇴" />
      </div>
    </aside>
  );
}
