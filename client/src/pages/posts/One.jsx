import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';

import { postBoardState } from '../../recoil/posts/postBoardState';

export default function One(props) {
  const navigation = useNavigate();

  const setPostBoard = useSetRecoilState(postBoardState);

  const postReadHandler = () => {
    // eslint-disable-next-line react/prop-types
    setPostBoard(props.items.boardId);
    navigation('/posts/read');
  };

  console.log(props);

  // eslint-disable-next-line react/prop-types
  return <button onClick={postReadHandler}>{props.items.title}</button>;
}
