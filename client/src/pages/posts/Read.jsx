import { useRecoilValue } from 'recoil';
import { postReadState } from '../../recoil/posts/postReadState';

export default function Read() {
  const postRead = useRecoilValue(postReadState);

  return <p>{postRead.title}</p>;
}
