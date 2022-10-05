import { useState } from 'react';
import { useRecoilValue } from 'recoil';
import { useNavigate } from 'react-router-dom';
import instance from '../../service/request';
import { memberIdState } from '../../recoil/atoms/memberIdState';

export default function ProfileEdit() {
  const [imgFile, setImgFile] = useState({
    url: '',
    file: '',
  });

  const [editValue, setEditValue] = useState({
    name: '',
    password: '',
  });

  const memberId = useRecoilValue(memberIdState);

  const navigation = useNavigate();

  const imgAddHandler = (e) => {
    setImgFile({
      url: URL.createObjectURL(e.target.files[0]),
      file: e.target.files,
    });
  };

  const nameValueHandler = (e) => {
    setEditValue({
      ...editValue,
      [e.target.name]: e.target.value,
    });
  };

  const cancelHandler = () => {
    navigation('/mypage');
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('memberId', memberId);
    if (editValue.name.trim() !== '') {
      formData.append('name', editValue.name);
    }
    if (editValue.password.trim() !== '') {
      formData.append('password', editValue.password);
    }
    if (imgFile.file.length > 0) {
      formData.append('image', imgFile.file[0]);
    }

    console.log(typeof formData.get('image'));

    try {
      await instance.patch(`/members/${memberId}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      alert('회원정보가 변경되었습니다 !');
      window.location.replace('/mypage');
    } catch (err) {
      console.log('err', err);
    }
  };
  return (
    <>
      <form
        className="flex flex-col items-center"
        method="post"
        encType="multipart/form-data"
      >
        {imgFile.url.trim().length === 0 && (
          <p className="text-center border-solid border border-zinc-300 w-44 h-44 m-2 bg-white">
            No Image
          </p>
        )}
        {imgFile.url.trim().length > 0 && (
          <img
            className="text-center border-solid border border-zinc-300 w-44 h-44 m-2 bg-white"
            src={imgFile.url}
            alt={`${imgFile.url}`}
          />
        )}

        <label
          className="bg-gray p-1.5 rounded"
          htmlFor="img"
          onChange={imgAddHandler}
        >
          사진 업로드
          <input className="hidden" id="img" type="file" accept="image/*" />
        </label>
        <p className="text-center m-2.5">username</p>
        <input
          className="p-0.5 mb-2"
          placeholder="이름 수정"
          name="name"
          value={editValue.name}
          onChange={nameValueHandler}
        />
        <input
          className="p-0.5"
          placeholder="비밀번호 수정"
          name="password"
          value={editValue.password}
          onChange={nameValueHandler}
        />
      </form>
      <div className="flex flex-col items-center mt-6">
        <button className="bg-gray w-44 mb-2" onClick={cancelHandler}>
          취소
        </button>
        <button className="bg-green w-44 mb-2 sm:mb-6" onClick={submitHandler}>
          적용
        </button>
      </div>
    </>
  );
}
