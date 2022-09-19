import { ReactNode } from 'react';

type ChildrenComponent = {
  children: ReactNode;
};

export default function Main(props: ChildrenComponent) {
  return <main>{props.children}</main>;
}
