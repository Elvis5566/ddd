package ordertaking.util

import zio.Tag
import zio.prelude.*
import zio.prelude.fx.ZPure

trait ZPureOps[W, S, R: Tag, E] {
  type Pure[+A] = ZPure[W, S, S, R, E, A]
  
  val unit: Pure[Unit]          = ZPure.unit
  def pure[A](a: A): Pure[A]    = ZPure.succeed(a)
  def fail(e: E): Pure[Nothing] = ZPure.fail(e)

  def inquire[A](f: R => A): Pure[A] = ZPure.serviceWith[R](f)

  val get: Pure[S]                       = ZPure.get
  def set(s: S): Pure[Unit]              = ZPure.set(s)
  def inspect[A](f: S => A): Pure[A]     = ZPure.modify(s => (f(s), s))
  def update[A](f: S => S): Pure[Unit]   = ZPure.update(f)
  def modify[A](f: S => (A, S)): Pure[A] = ZPure.modify(f)

  def log(w: W): Pure[Unit] = ZPure.log(w)

  def when(condition: Boolean)(program: Pure[Unit]): Pure[Unit] =
    ZPure.when(condition)(program).unit

  // we have a few more helpers
}
