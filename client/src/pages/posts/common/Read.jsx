import { lazy, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { postReadState } from '../../../recoil/selectors/postReadState';
import { commentReadState } from '../../../recoil/selectors/commentReadState';

const Comment = lazy(() => import('./Comment'));

export default function Read() {
  const postRead = useRecoilValue(postReadState);
  const commentRead = useRecoilValue(commentReadState);

  const navigation = useNavigate();

  const [commentValue, setCommentValue] = useState('');
  const [modal, setModal] = useState(false);

  const modalHandler = () => {
    setModal((prev) => !prev);
  };

  const editHandler = () => {
    navigation('/posts/edit');
  };

  const commentHandler = (e) => {
    setCommentValue(e.target.value);
  };

  const submitHandler = async () => {
    const item = {
      boardId: postRead.boardId,
      content: commentValue,
    };

    try {
      await axios.post(
        `http://211.41.205.19:8080/board/${postRead.boardId}/comment`,
        item,
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );
      alert('댓글이 등록되었습니다 !');
      window.location.replace('/posts/read');
    } catch (err) {
      console.log('err', err);
    }
  };

  return (
    <main className="flex flex-col justify-center py-5 px-5 bg-pink">
      <section className="flex justify-between">
        <p className="text-start">{postRead.title}</p>
        <div className="flex">
          {modal && (
            <>
              <button onClick={editHandler}>수정</button>
              <button>삭제</button>
            </>
          )}
          <button onClick={modalHandler}>&#65049;</button>
        </div>
      </section>
      <section className="bg-yellow w-full flex justify-center py-5">
        <div className="text-center border-solid border border-zinc-300 p-7 w-9/12 bg-white">
          사진
        </div>
      </section>
      <section className="flex justify-between bg-white">
        <div>
          <span className="mr-4 ml-1">👤 작성자</span>
          <span>{postRead.createdAt}</span>
        </div>
        <button className="mr-1">
          ❤️ {Math.floor(Math.random() * 100) + 1}
        </button>
      </section>
      <p>{postRead.content}</p>
      <span>댓글 {commentRead.length} 개</span>
      <input
        value={commentValue}
        className="w-full"
        placeholder="댓글 작성"
        onChange={commentHandler}
      />
      <div className="flex justify-end border-solid border-b-2">
        <button onClick={submitHandler}>댓글 달기 &rarr;</button>
      </div>
      {commentRead.map((comment) => (
        <Comment key={comment.commentId} items={comment} />
      ))}
    </main>
  );
}
