import { ReactNode } from 'react';

type Props = {
  children: ReactNode;
};

export default function Layout(props: Props) {
  return (
    <main className="flex justify-center items-center">
      {/* <div className=""></div> */}
      <section className="w-9/12 border-solid ">{props.children}</section>
      {/* <div className=""></div> */}
    </main>
  );
}
