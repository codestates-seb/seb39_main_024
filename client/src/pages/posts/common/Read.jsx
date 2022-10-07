import { lazy, useState } from 'react';
import { useRecoilValue } from 'recoil';
import { useNavigate } from 'react-router-dom';
import instance from '../../../service/request';
import { memberIdState } from '../../../recoil/atoms/memberIdState';
import { postReadState } from '../../../recoil/selectors/postReadState';
import { commentReadState } from '../../../recoil/selectors/commentReadState';
import { isLoginState } from '../../../recoil/atoms/isLoginState';
import ImageBox from './ImageBox';
import LikeCount from './LikeCount';

const Comment = lazy(() => import('./Comment'));

export default function Read() {
  const memberId = useRecoilValue(memberIdState);
  const isLogin = useRecoilValue(isLoginState);
  const postRead = useRecoilValue(postReadState);
  const commentRead = useRecoilValue(commentReadState);

  const [commentValue, setCommentValue] = useState('');
  const [modal, setModal] = useState(false);

  const navigation = useNavigate();

  // createdAt
  const createdAt = postRead.createdAt.split('T');
  const date = createdAt[0];
  const time = createdAt[1].slice(0, 5);

  // 수정, 삭제 모달
  const modalHandler = () => {
    setModal((prev) => !prev);
  };

  // 수정하기 버튼
  const editHandler = () => {
    navigation('/posts/edit');
  };

  // 삭제하기 버튼
  const deleteHandler = async () => {
    let item = { memberId: memberId };
    try {
      await instance.delete(
        `/board/${postRead.boardId}`,
        { data: item },
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      ),
        alert('해당 글이 삭제되었습니다.');
      window.location.replace('/posts');
    } catch (err) {
      console.log('err', err);
    }
  };

  // 댓글 인풋 값 핸들러
  const commentHandler = (e) => {
    setCommentValue(e.target.value);
  };

  const loginPageHandler = () => {
    alert('로그인이 필요한 서비스입니다 !');
    navigation('/login');
  };

  // 댓글 달기 버튼
  const submitHandler = async () => {
    if (commentValue.trim() === '') {
      alert('최소 한 글자 이상 작성해주세요 !');
      return;
    }

    const item = {
      memberId: memberId,
      boardId: postRead.boardId,
      content: commentValue,
    };

    try {
      await instance.post(`/board/${postRead.boardId}/comment`, item, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      alert('댓글이 등록되었습니다 !');
      window.location.replace('/posts/read');
    } catch (err) {
      console.log('err', err);
    }
  };

  const isValid = Number(memberId) === postRead.memberId;

  return (
    <main className="flex flex-col justify-center py-5 px-5 mb-10">
      <section className="flex justify-between">
        <p className="text-start text-3xl">{postRead.title}</p>
        {isValid && (
          <div className="flex">
            {modal && (
              <>
                <button className="mr-2" onClick={editHandler}>
                  수정
                </button>
                <button className="mr-2" onClick={deleteHandler}>
                  삭제
                </button>
              </>
            )}
            <button onClick={modalHandler}>&#65049;</button>
          </div>
        )}
      </section>
      <ImageBox postRead={postRead} />
      <section className="flex justify-between bg-white">
        <div className="flex items-center">
          <img
            className="w-6 h-6 mr-3 rounded-full"
            src={postRead.memberImage}
            alt="memberImage"
          />
          <span className="mr-3">{postRead.memberName}</span>
          <span>{`${date} ${time}`}</span>
        </div>
        <LikeCount like={postRead.likeCount} />
      </section>
      <p className="sm:text-2xl text-3xl my-5">{postRead.content}</p>
      <span className="my-3">댓글 {commentRead.length} 개</span>
      {isLogin && (
        <input
          value={commentValue}
          className="w-full border-solid border border-slate-500 rounded-md p-2"
          placeholder="댓글 작성"
          onChange={commentHandler}
        />
      )}
      {!isLogin && (
        <input
          className="w-full border-solid border border-slate-500 rounded-md p-2"
          placeholder="로그인 후 이용해주세요."
        />
      )}
      <div className="flex justify-end border-solid border-b-2">
        {isLogin && (
          <button className="py-3" onClick={submitHandler}>
            댓글 달기 &rarr;
          </button>
        )}
        {!isLogin && (
          <button className="py-3" onClick={loginPageHandler}>
            로그인 &rarr;
          </button>
        )}
      </div>
      {commentRead.map((comment) => (
        <Comment key={comment.commentId} items={comment} postRead={postRead} />
      ))}
    </main>
  );
}
