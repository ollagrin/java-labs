<nav class="border-gray-200 bg-gray-900">
    <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
        <span class="self-center text-2xl font-semibold whitespace-nowrap text-white">Students Service</span>
        <div class="items-center justify-between hidden w-full md:flex md:w-auto md:order-1" id="navbar-cta">
            <ul class="flex flex-col font-medium p-4 md:p-0 mt-4 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0
            md:border-0 bg-gray-900 border-gray-700">
                <li>
                    <a href="${pageContext.request.contextPath}/start"
                       class="block py-2 pl-3 pr-4 text-white rounded md:bg-transparent
                       md:p-0 hover:text-blue-500" aria-current="page">Students</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/grades?page=show"
                       class="block py-2 pl-3 pr-4 text-white rounded md:bg-transparent
                       md:p-0 hover:text-blue-500">Grades</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
