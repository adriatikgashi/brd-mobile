/**
 * BreadWallet
 *
 * Created by Pablo Budelli <pablo.budelli@breadwallet.com> on 11/05/19.
 * Copyright (c) 2019 breadwallet LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.breadwallet.ui.settings.segwit

import com.spotify.mobius.Next
import com.spotify.mobius.Next.dispatch
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object LegacyAddressUpdate : Update<LegacyAddressModel, LegacyAddressEvent, LegacyAddressEffect>,
    LegacyAddressUpdateSpec {

    override fun update(
        model: LegacyAddressModel,
        event: LegacyAddressEvent
    ): Next<LegacyAddressModel, LegacyAddressEffect> = patch(model, event)

    override fun onShareClicked(model: LegacyAddressModel): Next<LegacyAddressModel, LegacyAddressEffect> =
        dispatch(setOf(LegacyAddressEffect.ShareAddress(model.receiveAddress, model.walletName)))

    override fun onAddressClicked(model: LegacyAddressModel): Next<LegacyAddressModel, LegacyAddressEffect> =
        dispatch(setOf(LegacyAddressEffect.CopyAddressToClipboard(model.sanitizedAddress)))

    override fun onCloseClicked(model: LegacyAddressModel): Next<LegacyAddressModel, LegacyAddressEffect> =
        dispatch(setOf(LegacyAddressEffect.GoBack))

    override fun onAddressUpdated(
        model: LegacyAddressModel,
        event: LegacyAddressEvent.OnAddressUpdated
    ): Next<LegacyAddressModel, LegacyAddressEffect> =
        next(
            model.copy(
                receiveAddress = event.receiveAddress,
                sanitizedAddress = event.sanitizedAddress
            )
        )

    override fun onWalletNameUpdated(
        model: LegacyAddressModel,
        event: LegacyAddressEvent.OnWalletNameUpdated
    ): Next<LegacyAddressModel, LegacyAddressEffect> =
        next(model.copy(walletName = event.name))
}
