import Image from "next/image";
import Link from "next/link";

export default function Home() {
  return (
    <div className={"p-10"}>
      <h1 className={"font-bold"}>Babysitter Manager</h1>
      <p><Link href="/babysitters"> Babysitter </Link></p>
      <p><Link href="/roles"> Roles </Link></p>
          <p><Link href="/users"> Users </Link></p>
    </div>
  );
}
