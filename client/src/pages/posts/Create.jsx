import React, { useState, useMemo } from 'react';
import axios from 'axios';

export default function Create() {
  const [inputValue, setInputValue] = useState({
    title: '',
    content: '',
  });

  const [imgFile, setImgFile] = useState([]);

  // const fileInput = useRef<HTMLInputElement>(null);

  const inputValueChangeHandler = (e) => {
    setInputValue({
      ...inputValue,
      [e.target.name]: e.target.value,
    });
  };

  const imgAddHandler = useMemo(
    () => (e) => {
      const imgList = e.target.files;
      const imgUrlList = [...imgFile];

      // for (let i = 0; i < imgList.length; i++) {
      const currentImageUrl = URL.createObjectURL(imgList);
      imgUrlList.push(currentImageUrl);
      // }

      // if (imgUrlList.length > 5) {
      //   imgUrlList = imgUrlList.slice(0, 5);
      // }

      setImgFile(imgUrlList);
    },
    [imgFile]
  );

  const imgDeleteHandler = (id) => {
    setImgFile(imgFile.filter((_, index) => index !== id));
  };

  const submitHandler = async (e) => {
    e.preventDefault();

    const item = {
      title: inputValue.title,
      content: inputValue.content,
    };

    try {
      await axios.post('http://211.41.205.19:8080/board', item, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      alert('글이 등록되었습니다 !');
      window.location.replace('/posts');
    } catch (err) {
      console.log('err', err);
    }
  };

  return (
    <form className="border-solid bg-pink" onSubmit={submitHandler}>
      <div>
        <select name="카테고리">
          <option value="select">카테고리 선택</option>
          <option value="record">운동 기록</option>
          <option value="meal">다이어트 식단</option>
          <option value="free">자유</option>
        </select>
        <button>글 등록</button>
      </div>
      <div>
        {/* <button onClick={fileHandler}>파일 업로드</button> */}

        <input
          // className="hidden"
          type="file"
          id="input-file"
          multiple
          accept="image/*"
          onChange={imgAddHandler}
        />
      </div>
      {imgFile.map((img, id) => (
        <div key={id}>
          <img src={img} alt={`${img}-${id}`} />
          <button onClick={() => imgDeleteHandler(id)}>삭제</button>
        </div>
      ))}
      <div>
        <input
          name="title"
          value={inputValue.title}
          onChange={inputValueChangeHandler}
          placeholder="제목을 입력하세요"
        />
      </div>
      <div>
        <textarea
          name="content"
          value={inputValue.content}
          onChange={inputValueChangeHandler}
          placeholder="내용을 입력하세요"
        />
      </div>
    </form>
  );
}
