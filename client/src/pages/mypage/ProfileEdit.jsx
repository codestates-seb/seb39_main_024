import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import MyPage from './MyPage';

export default function ProfileEdit() {
  const [imgFile, setImgFile] = useState('');
  const [nameValue, setNameValue] = useState('');

  const navigation = useNavigate();

  const imgAddHandler = (e) => {
    setImgFile(URL.createObjectURL(e.target.files[0]));
  };

  const nameValueHandler = (e) => {
    setNameValue(e.target.value);
  };

  const cancelHandler = () => {
    navigation('/mypage');
  };

  const submitHandler = (e) => {
    e.preventDefault();
  };
  return (
    <MyPage>
      <form className="flex flex-col items-center">
        {imgFile.trim().length === 0 && (
          <p className="text-center border-solid border border-zinc-300 w-44 h-44 m-2 bg-white">
            No Image
          </p>
        )}
        {imgFile.trim().length > 0 && (
          <img
            className="text-center border-solid border border-zinc-300 w-44 h-44 m-2 bg-white"
            src={imgFile}
            alt={`${imgFile}`}
          />
        )}

        <label
          className="bg-gray p-1.5 rounded"
          htmlFor="img"
          onChange={imgAddHandler}
        >
          사진 업로드
          <input
            className="hidden"
            id="img"
            type="file"
            accept="image/*"
            value={nameValue}
            onChange={nameValueHandler}
          />
        </label>
        <p className="text-center m-2.5">username</p>
        <input className="p-0.5" placeholder="이름" />
      </form>
      <div className="flex flex-col items-center mt-6">
        <button className="bg-gray w-44 mb-2" onClick={cancelHandler}>
          취소
        </button>
        <button className="bg-green w-44 mb-2 sm:mb-6" onSubmit={submitHandler}>
          적용
        </button>
      </div>
    </MyPage>
  );
}
